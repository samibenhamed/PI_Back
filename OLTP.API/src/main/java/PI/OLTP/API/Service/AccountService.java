package PI.OLTP.API.Service;

import PI.OLTP.API.DTO.ImageDTO;
import PI.OLTP.API.DTO.LocDto;
import PI.OLTP.API.DTO.RequestDtos.account.account.detailed.DetailedAccountDTO;
import PI.OLTP.API.DTO.RequestDtos.account.account.personal.AccountDTO;
import PI.OLTP.API.DTO.RequestDtos.account.account.personal.AdminDTO;
import PI.OLTP.API.DTO.RequestDtos.account.account.personal.UserDTO;
import PI.OLTP.API.DTO.RequestDtos.account.account.undetailed.UndetailedAccountDTO;
import PI.OLTP.API.DTO.RequestDtos.account.account.undetailed.UndetailedAdminDTO;
import PI.OLTP.API.DTO.RequestDtos.account.account.undetailed.UndetailedUserDTO;
import PI.OLTP.API.DTO.ResponseDtos.LocationDTO;
import PI.OLTP.API.DTO.ResponseDtos.Skill.NewSkillResponseDTO;
import PI.OLTP.API.Model.*;
import PI.OLTP.API.Repository.AccountRepository;
import PI.OLTP.API.Repository.DiplomaRepository;
import PI.OLTP.API.Repository.ImageRepository;
import PI.OLTP.API.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository ;
    private  final JobRepository jobRepository ;
    private  final DiplomaRepository diplomaRepository ;
    private ImageRepository imageRepository ;
    private UserService userService ;
    @Autowired
    public  AccountService(AccountRepository accountRepository , JobRepository jobRepository , DiplomaRepository diplomaRepository ,  ImageRepository imageRepository ,  UserService userService){
        this.accountRepository=accountRepository;
        this.jobRepository=jobRepository ;
        this.diplomaRepository =diplomaRepository ;
        this.imageRepository =imageRepository ;
        this.userService=userService ;
    }
    public List<UndetailedAccountDTO> getAllAccounts(){
        List<Account> accounts = accountRepository.findAll();
        List<UndetailedAccountDTO> result = new ArrayList<>() ;
        for (Account account : accounts) {
            if(account.getDtype().equals("User")){
                UndetailedUserDTO dto = new UndetailedUserDTO().toUndetailedUserDTO((User) account ) ;
                Image image = this.imageRepository.getImageByAccount(account) ;
                dto.setImage(new ImageDTO().toDTO(image));
                List<NewSkillResponseDTO> skills = this.userService.getAllSkils(account.getId()) ;
                List<String> skillsNames = new ArrayList<>() ;
                for (NewSkillResponseDTO skill:skills) {
                    skillsNames.add(skill.getName()) ;
                }
                dto.setSkills(skillsNames);
//
                result.add(dto ) ;
            }
            else if(account.getDtype().equals("Admin")){
                UndetailedAdminDTO dto = new UndetailedAdminDTO().toUndetailedAdminDTO((Admin) account) ;
                Image image = this.imageRepository.getImageByAccount(account) ;
                dto.setImage(new ImageDTO().toDTO(image));
                result.add(dto ) ;
            }
        }
        return  result ;

    }


    //
    public  boolean uniqueEmail(String email){

        Account account = accountRepository.findAccountByEmail(email) ;
        if (account!=null){
            return  false ;
        }
        else {
            return true ;
        }
    }

    public boolean uniquePassWord(String passWord ){
        Account account = accountRepository.findAccountByPassWord(passWord) ;
        if (account!=null){
            return  false ;
        }
        else {
            return true ;
        }
    }


    //
    public AccountDTO getPersonalAccountDetails(Long id ){
        Account account = accountRepository.findById(id).orElseThrow() ;

        if(account.getDtype().equals("Admin")){
            Admin admin = (Admin) account ;
            Image image = imageRepository.getImageByAccount(account) ;
            AdminDTO response =  AdminDTO.entityToDTO(admin , image) ;
            //
            Location origine =admin.getOrigin() ;
            response.setOrigine(LocationDTO.entityToDTO(origine));
            return response ;
        }
        else {
            //
            User user = (User) account ;
            Image image = imageRepository.getImageByAccount(account) ;

            UserDTO response =  UserDTO.entityToDto(user,image) ;
            //
            Location origine =user.getOrigin() ;
            LocationDTO origineDTO = LocationDTO.entityToDTO(origine) ;
            response.setOrigine(origineDTO);
            //
            return response ;
        }

    }

    //
    public ImageDTO updateImage(Long accountId , MultipartFile imageFile ) throws IOException {
        // Get Account
        Account account = this.accountRepository.findById(accountId).orElseThrow() ;
        // Get Image
        Image image = this.imageRepository.getImageByAccount(account) ;
        image.setType(imageFile.getContentType());
        image.setBytes(imageFile.getBytes());
        image.setName(imageFile.getOriginalFilename());
        this.imageRepository.save(image) ;
        return new ImageDTO().toDTO(image) ;
    }

    //
    public void deleteImage(long accountId ){
        // Get Account
        Account account = this.accountRepository.findById(accountId).orElseThrow() ;
        // Get Image
        Image image = this.imageRepository.getImageByAccount(account) ;
        image.setType(null);
        image.setBytes(null);
        image.setName(null);
        this.imageRepository.save(image) ;

    }

   //



    // for Other Users ...
    public DetailedAccountDTO getAccountDetailsById(Long id ){
//        Account account  = accountRepository.findById(id).orElseThrow() ;
//        if (account.getDtype().equals("Admin")){
//
//            DetailedAdminDTO response = new DetailedAdminDTO() ;
//            Admin admin = (Admin) account ;
//
//            response.toDetailedAdminDTO(admin) ;
//            Location origine = account.getOrigin() ;
//            response.setOrigine(LocationDTO.entityToDTO(origine));
//            return  response  ;
//        }
//        else if (account.getDtype().equals("User")){
//            DetailedUserDTO response = new DetailedUserDTO() ;
//            User user =(User) account ;
//            response.toDTO(user) ;
//            //
//            Location origine = account.getOrigin() ;
//            response.setOrigine(LocationDTO.entityToDTO(origine));
//            //
//            List<Job> jobs = jobRepository.getAllByUser(user) ;
//            List<UndetailedJobDTO> undetailedJobDTOS = new ArrayList<>() ;
//            for (Job curJob:jobs) {
//                undetailedJobDTOS.add(new UndetailedJobDTO().toDTO(curJob)) ;
//            }
//            response.setJobs(undetailedJobDTOS);
//
//            //
//            List<Diploma> diplomas = diplomaRepository.getAllByUser(user) ;
//            List<DiplomaResponseDTO> diplomaResponseDTOS = new ArrayList<>() ;
//            for (Diploma curDiploma: diplomas) {
//                diplomaResponseDTOS.add(new DiplomaResponseDTO().toDTO(curDiploma)) ;
//            }
//            response.setDiplomas(diplomaResponseDTOS);
//            return response ;
//
//        }
        return  null ;


        // Populate Origine
        // User Case

    }





}
