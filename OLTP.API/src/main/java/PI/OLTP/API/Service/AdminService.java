package PI.OLTP.API.Service;

import PI.OLTP.API.DTO.RequestDtos.account.newAccount.NewAdminDTO;
import PI.OLTP.API.DTO.RequestDtos.account.newAccount.NewUserDTO;
import PI.OLTP.API.DTO.RequestDtos.account.update_account.UpdateAdminRequestDTO;
import PI.OLTP.API.DTO.ResponseDtos.AdminUniversity;
import PI.OLTP.API.DTO.ResponseDtos.account.UpdateAccountResponseDTO;
import PI.OLTP.API.DTO.ResponseDtos.account.UpdateAdminResponseDTO;
import PI.OLTP.API.Model.*;
import PI.OLTP.API.Repository.*;
import org.springframework.stereotype.Service;

import PI.OLTP.API.DTO.RequestDtos.account.account.personal.AdminDTO;
import PI.OLTP.API.DTO.ResponseDtos.LocationDTO;
import PI.OLTP.API.DTO.RequestDtos.account.account.personal.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AdminService {
    private final AccountRepository accountRepo ;
    private final AdminRepository adminRepo ;
    private final UserRepository userRepo ;
    private final LocationService locationService ;
    private final ImageRepository imageRepository ;
    private final DiplomaRepository diplomaRepository ;
    private final JobRepository jobRepository ;
    private  final SkillRepository skillRepository ;
    @Autowired
    public  AdminService( AccountRepository accountRepo ,  AdminRepository adminRepo ,  UserRepository userRepo ,  LocationService locationService , ImageRepository imageRepository
    ,  DiplomaRepository diplomaRepository , JobRepository jobRepository , SkillRepository skillRepository
    ){
        this.accountRepo=accountRepo ;
        this.adminRepo=adminRepo;
        this.userRepo=userRepo;
        this.locationService=locationService;
        this.imageRepository=imageRepository ;
        this.diplomaRepository=diplomaRepository ;
        this.jobRepository=jobRepository ;
        this.skillRepository=skillRepository ;
    }

   //


    //
    public AdminDTO addAdmin(NewAdminDTO data , MultipartFile imageFile ) throws IOException {
        // Create A Admin
        Admin admin = new Admin();
        // GET Admin Origine Entity
        Long originLocationId = data.getOrigin() ;
        System.out.println(originLocationId);
        Location origin = locationService.getLocationById(originLocationId) ;

        //  populate admin Origin Property
        admin.setOrigin(origin);
        // Populate Admin Properties
        admin.setFirstName(data.getFirstName());
        admin.setLastName(data.getLastName());
        admin.setEmail(data.getEmail());
        admin.setPassWord(data.getPassWord());
        admin.setUniversity(data.getUniversity());
        admin.setBirthDate(data.getBirthDate());
        admin.setSex(data.getSex());
        admin.setPhone(data.getPhone());
        admin.setTitle(data.getTitle());
        admin.setLinkedinURL(data.getLinkedinURL());
        admin.setPersonalWesiteURL(data.getPersonalWesiteURL()); ;
        admin.setProtfolioURL(data.getProtfolioURL());
         //


        //

        Image image = new Image();
        image.setAccount(admin);
        admin.setImage(image) ;

        if (imageFile.getContentType().equals("text/plain")!=true )  {
            image.setType(imageFile.getContentType());
            image.setBytes(imageFile.getBytes());
            image.setName(imageFile.getOriginalFilename());
        }
        //
        adminRepo.save(admin);
        imageRepository.save(image) ;

        // create adminDTO
        AdminDTO adminDTO =AdminDTO.entityToDTO(admin , image);

        adminDTO.setOrigine(LocationDTO.entityToDTO(origin));


        return adminDTO ;
    }

    //
    public UserDTO addUser(NewUserDTO data ,  MultipartFile imageFile ) throws IOException  {
        // create user
        User user = new User() ;

        // populate user properties
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setEmail(data.getEmail());
        user.setPassWord(data.getPassWord());
        user.setUniversity(data.getUniversity());
        user.setBirthDate(data.getBirthDate());
        user.setSex(data.getSex());
        user.setPhone(data.getPhone());

        user.setTitle(data.getTitle());
        user.setLinkedinURL(data.getLinkedinURL());
        user.setPersonalWesiteURL(data.getPersonalWesiteURL()); ;
        user.setProtfolioURL(data.getProtfolioURL());
        Long originLocationId = data.getOrigin() ;
        Location origin = locationService.getLocationById(originLocationId) ;
        user.setOrigin(origin);
        user.setPromotion(data.getPromotion());
        user.setGraduationDate(data.getGraduationDate());
        user.setBacType(data.getBacType());
        user.setSatisfactionLevel(data.getSatisfactionLevel());
        user.setStatus(data.getStatus());

        //
        //

        Image image = new Image();
        image.setAccount(user);
        user.setImage(image) ;

        if (imageFile.getContentType().equals("text/plain")!=true )  {
            image.setType(imageFile.getContentType());
            image.setBytes(imageFile.getBytes());
            image.setName(imageFile.getOriginalFilename());
        }
        //
        userRepo.save(user);
        imageRepository.save(image) ;
        // Save User

        // User Dto
        UserDTO userDTO = UserDTO.entityToDto(user,image);
        userDTO.setOrigine(LocationDTO.entityToDTO(origin));

        return userDTO ;
    }

    //
    public UpdateAdminResponseDTO updatePersonalAccount(Long id , UpdateAdminRequestDTO data ){
        Admin admin = adminRepo.findById(id).orElseThrow() ;
        admin.setFirstName(data.getFirstName());
        admin.setLastName(data.getLastName());
        admin.setEmail(data.getEmail());
        admin.setPassWord(data.getPassWord());
        admin.setBirthDate(data.getBirthDate());
        admin.setSex(data.getSex());
        admin.setPhone(data.getPhone());
        admin.setTitle(data.getTitle());
        admin.setDescription(data.getDescription());
        admin.setLinkedinURL(data.getLinkedinURL());
        admin.setPersonalWesiteURL(data.getPersonalWesiteURL());
        admin.setProtfolioURL(data.getProtfolioURL());
        //
        Location origine = locationService.getLocationById( data.getOrigineId() ) ;
        admin.setOrigin(origine)  ;

        accountRepo.save(admin) ;
        UpdateAdminResponseDTO response = new UpdateAdminResponseDTO().toDto(admin) ;
        return response ;

    }

    public AdminUniversity getAdminUniversity(Long id ){
        Admin admin = adminRepo.findById(id).orElseThrow();
        AdminUniversity adminUniversity= new AdminUniversity() ;
        adminUniversity.setAdminUniversity(admin.getUniversity());
        return adminUniversity ;
    }

    public  void deleteAccount(Long accountId ){

        Account account = accountRepo.findById(accountId).orElseThrow() ;
        if(account.getDtype().equals("User" ) ){
            List<Job> jobs = this.jobRepository.getAllByUser( (User) account) ;
            System.out.println(jobs);
            for (Job job:jobs) {
                this.jobRepository.delete(job);
            }
        }

        this.accountRepo.deleteById(accountId);
    }

    //


}
