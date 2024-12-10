package PI.OLTP.API.Service;

import PI.OLTP.API.DTO.RequestDtos.SatisfactionLevel;
import PI.OLTP.API.DTO.RequestDtos.Skill.NewSkillRequestDTO;
import PI.OLTP.API.DTO.RequestDtos.account.update_account.UpdateUserRequestDTO;
import PI.OLTP.API.DTO.RequestDtos.diploma.NewDiploma;
import PI.OLTP.API.DTO.RequestDtos.job.NewJob;
import PI.OLTP.API.DTO.ResponseDtos.LocationDTO;
import PI.OLTP.API.DTO.RequestDtos.account.account.personal.UserDTO;
import PI.OLTP.API.DTO.ResponseDtos.Skill.NewSkillResponseDTO;
import PI.OLTP.API.DTO.ResponseDtos.account.UpdateUserResponseDTO;
import PI.OLTP.API.DTO.ResponseDtos.diploma.DiplomaResponseDTO;
import PI.OLTP.API.DTO.ResponseDtos.job.PersonalJobDetailsDTO;
import PI.OLTP.API.DTO.ResponseDtos.job.UndetailedJobDTO;
import PI.OLTP.API.Model.*;
import PI.OLTP.API.Repository.DiplomaRepository;
import PI.OLTP.API.Repository.JobRepository;
import PI.OLTP.API.Repository.SkillRepository;
import PI.OLTP.API.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository ;
    private final  LocationService locationService ;
    private final JobRepository jobRepository ;
    private final DiplomaRepository diplomaRepository ;
    private final SkillRepository skillRepository ;

    @Autowired
    public UserService (UserRepository userRepository , LocationService locationService , JobRepository jobRepository , DiplomaRepository diplomaRepository ,  SkillRepository skillRepository){
        this.userRepository=userRepository;
        this.locationService=locationService ;
        this.jobRepository=jobRepository ;
        this.diplomaRepository=diplomaRepository ;
        this.skillRepository =skillRepository ;
    }


    //
    public UpdateUserResponseDTO updatePersonalAccount(Long id , UpdateUserRequestDTO data){
        User user = userRepository.findById(id).orElseThrow() ;
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setEmail(data.getEmail());
        user.setPassWord(data.getPassWord());
        user.setBirthDate(data.getBirthDate());
        user.setSex(data.getSex());
        user.setPhone(data.getPhone());
        user.setTitle(data.getTitle());
        user.setDescription(data.getDescription());
        user.setLinkedinURL(data.getLinkedinURL());
        user.setPersonalWesiteURL(data.getPersonalWesiteURL());
        user.setProtfolioURL(data.getProtfolioURL());
        user.setPromotion(data.getPromotion());
        user.setGraduationDate(data.getGraduationDate());
        user.setBacType(data.getBacType());
        user.setSatisfactionLevel(data.getSatisfactionLevel());


        Location origine = locationService.getLocationById( data.getOrigineId() ) ;
        user.setOrigin(origine)  ;

        userRepository.save(user) ;
        UpdateUserResponseDTO response = new UpdateUserResponseDTO().toDto(user) ;
        return response  ;
    }

    //
    public int getSatisfactionLevel(Long id ){

        User user = userRepository.findById(id).orElseThrow();
         return  user.getSatisfactionLevel() ;
    }
    //
    public int updateSatisfactionLevel (SatisfactionLevel stLevel){
        User user = userRepository.findById(stLevel.getUserId()).orElseThrow() ;
        user.setSatisfactionLevel(stLevel.getSatisfactionLevel());
        userRepository.save(user) ;
        return user.getSatisfactionLevel() ;

    }



    // *********************** Job ***************************
    //
    public  List<UndetailedJobDTO> getAllJobs(Long userId){
        User user = this.userRepository.findById(userId).orElseThrow() ;
        List<Job> jobs = this.jobRepository.getAllByUser(user) ;
        List<UndetailedJobDTO> response = new ArrayList<>() ;
        for (Job job:jobs) {
            response.add(new UndetailedJobDTO().toDTO(job)) ;
        }
        return  response ;
    }
    //
    public PersonalJobDetailsDTO addJob(Long userId , NewJob data ){
        //
        User user = userRepository.findById(userId).orElseThrow() ;

        // Still Working in the New Job // Has Other Jobs // Studying
        boolean stillWorking =false ;

        if (data.getQuitDate()==null ){
            stillWorking =true ;
        }
        else if (data.getQuitDate().compareTo(LocalDate.now())>0){
            stillWorking=true ;
        }

        if (user.getStatus()!=null ){
            if(user.getStatus().equals("Study") && stillWorking ){
                user.setStatus("work_study");
            }
        }
        else if (user.getStatus()==null && stillWorking){
            user.setStatus("Work");
        }
        userRepository.save(user) ;
        //
        Job job = new Job() ;

        job.setUser(user);

        Location companyLocation = locationService.getLocationById(data.getCompanyLocationId()) ;
        job.setCompanyLocation(companyLocation);

        job.setCompanyName(data.getCompanyName());

        job.setCompanyWebsiteURL(data.getCompanyWebsiteURL());

        job.setCompanySector(data.getCompanySector());

        job.setJobField(data.getJobField());

        job.setJobTitle(data.getJobTitle());


        job.setHireDate(data.getHireDate());

        job.setQuitDate(data.getQuitDate());

        job.setSalary(data.getSalary());

        job.setSalaryCurrency(data.getSalaryCurrency());

        job.setNbApplications(data.getNbApplications());

        job.setNbRefusedApplications(data.getNbRefusedApplications());

        //
        jobRepository.save(job) ;
        //
        PersonalJobDetailsDTO result = new PersonalJobDetailsDTO().toDTO(job) ;
        return  result ;

    }
    //

    public PersonalJobDetailsDTO updateJob(Long jobId ,NewJob data  ){
        Job job = jobRepository.findById(jobId).orElseThrow() ;
        //
        Location companyLocation = locationService.getLocationById(data.getCompanyLocationId()) ;
        job.setCompanyLocation(companyLocation);

        job.setCompanyName(data.getCompanyName());

        job.setCompanyWebsiteURL(data.getCompanyWebsiteURL());

        job.setCompanySector(data.getCompanySector());

        job.setJobField(data.getJobField());

        job.setJobTitle(data.getJobTitle());

        job.setHireDate(data.getHireDate());

        job.setQuitDate(data.getQuitDate());

        job.setSalary(data.getSalary());

        job.setSalaryCurrency(data.getSalaryCurrency());

        job.setNbApplications(data.getNbApplications());

        job.setNbRefusedApplications(data.getNbRefusedApplications());
        jobRepository.save(job) ;

        // Update User Status
           // Check if Has Other Job
        User user = job.getUser() ;
        List<Job> jobs =jobRepository.getAllByUser(user);

        Boolean hasOtherJob=false ;

        for (Job currJob:jobs) {

            if(currJob.getQuitDate()==null ){
                hasOtherJob=true ;
                break ;
            }
            else if (currJob.getQuitDate().compareTo(LocalDate.now())>0  ){
                System.out.println("1111");
                hasOtherJob=true ;
                break ;
            }
        }

          // Update User Status Bases On CurrStatus
        LocalDate currDate = LocalDate.now() ;
        if(user.getStatus()==null){
            if (job.getQuitDate().compareTo(currDate)>0){
                user.setStatus("Work");
            }
        }
        else if(user.getStatus().equals("Study") && job.getQuitDate().compareTo(currDate)>0 ){
            user.setStatus("work_study");
        }
        else if(job.getQuitDate()!=null){
            if(user.getStatus().equals("work_study") && job.getQuitDate().compareTo(currDate)<=0 && !hasOtherJob )
            {
                user.setStatus("Study");
            }else if (job.getQuitDate().compareTo(currDate)<=0 && !hasOtherJob ){
                user.setStatus(null);
            }
        }
        userRepository.save(user) ;
        job.setUser(user);

        //
        PersonalJobDetailsDTO result = new PersonalJobDetailsDTO().toDTO(job) ;
        return  result ;
    }

    //
    public void deleteJob(Long jobId){
        Job job = jobRepository.findById(jobId).orElseThrow();
        User user = job.getUser() ;
        List<Job> jobs =jobRepository.getAllByUser(user);

        jobs.remove(job) ;
        Boolean hasOtherJob=false ;

        for (Job currJob:jobs) {

            if(currJob.getQuitDate()==null ){
                hasOtherJob=true ;
                break ;
            }
            else if (currJob.getQuitDate().compareTo(LocalDate.now())>0  ){
                System.out.println("1111");
                hasOtherJob=true ;
                break ;
            }
        }

        if (user.getStatus()==null || hasOtherJob){
            user.setStatus("Work");
        }
        if (user.getStatus()!=null ){
            if (!hasOtherJob && user.getStatus().equals("work_study") ){
                user.setStatus("Study") ;
            }
            else if (!hasOtherJob && user.getStatus().equals("Study") ){
                user.setStatus("Study") ;
            }
            else if (!hasOtherJob){
                user.setStatus(null) ;
            }
        }

        userRepository.save(user) ;
        jobRepository.delete(job);
    }

    //
    public PersonalJobDetailsDTO getJobDetails(Long jobId){
        Job job = jobRepository.findById(jobId).orElseThrow() ;
        PersonalJobDetailsDTO result = new PersonalJobDetailsDTO().toDTO(job) ;
        return  result ;

    }


    //************************************** Diploma ******************
    //
    public DiplomaResponseDTO addDiploma( Long userId, NewDiploma data){
        User user = userRepository.findById(userId).orElseThrow() ;
        Diploma diploma = new Diploma() ;
        //
        Location universityLocation = locationService.getLocationById(data.getUniversityLocationId()) ;
        diploma.setUniversityLocation(universityLocation);
        //
        diploma.setUniversityName(data.getUniversityName());
        diploma.setDiplomaType(data.getDiplomaType());
        diploma.setStudyField(data.getStudyField());
        diploma.setEnrollmenDate(data.getEnrollmenDate());
        diploma.setGraduationDate(data.getGraduationDate()) ;
        //
        diploma.setUser(user);

        diplomaRepository.save(diploma) ;

        // Update User Status : Based On Graduation Date // Othe Diplomas Enrolement // Current Status
        // Chech if  Still Studying
        boolean stillSudy = false ;
        // Chech Based On Graduation Date
        List<Diploma> diplomas = diplomaRepository.getAllByUser(user);
        for (Diploma currDiploma :diplomas) {
            System.out.println(currDiploma.getGraduationDate());

            if (currDiploma.getGraduationDate()==null ){
                 stillSudy = true  ;
            }
            else if(currDiploma.getGraduationDate().isAfter(LocalDate.now()) ) {
                System.out.println("1");
                stillSudy = true  ;
            }
        }
        // Update User Status
        if (user.getStatus()==null){
            if (stillSudy){
                user.setStatus("Study");
            }
        }
        else if (user.getStatus().equals("Work") && stillSudy ){
            user.setStatus("work_study");
        }

        userRepository.save(user) ;


        // Current Status null Study Work Work Study

        //
        return new DiplomaResponseDTO().toDTO(diploma) ;
    }

    //
    public DiplomaResponseDTO updateDiploma(Long diplomaId , NewDiploma data ){
        Diploma diploma = diplomaRepository.findById(diplomaId).orElseThrow() ;
        // Update Diploma
        //
        Location universityLocation = locationService.getLocationById(data.getUniversityLocationId()) ;
        diploma.setUniversityLocation(universityLocation);
        //
        diploma.setUniversityName(data.getUniversityName());
        diploma.setDiplomaType(data.getDiplomaType());
        diploma.setStudyField(data.getStudyField());
        diploma.setEnrollmenDate(data.getEnrollmenDate());
        diploma.setGraduationDate(data.getGraduationDate()) ;
        diplomaRepository.save(diploma);

        // Update User Status

        User user = diploma.getUser();
        List<Diploma> diplomas = diplomaRepository.getAllByUser(user);
        System.out.println(diplomas.size());
        boolean stillStudy = false ;

        // Chech if Still Study based On Graduation Date
        for (Diploma currDiploma :diplomas) {
            if (currDiploma.getGraduationDate()==null ){
                stillStudy = true  ;
                break;
            }
            else if(currDiploma.getGraduationDate().isAfter(LocalDate.now()) ) {
                stillStudy = true  ;
                break ;
            }
        }
        // Update User Status based On Status and if stillStudy
        if (user.getStatus()==null){
            if (stillStudy){
                user.setStatus("Study");
            }
        }
        else if(user.getStatus().equals("Study") && !stillStudy){
            user.setStatus(null);
//
        }
        else if (user.getStatus().equals("Work") && stillStudy ){
            user.setStatus("work_study");
        }
        else if (user.getStatus().equals("work_study") && !stillStudy ){
            user.setStatus("Work");
        }
        userRepository.save(user) ;

        return new DiplomaResponseDTO().toDTO(diploma) ;
    }

    // Delete Diploma
    public  void  deleteDiploma(Long diplomaId){

        Diploma diploma = diplomaRepository.findById(diplomaId).orElseThrow() ;
        User user = diploma.getUser() ;
        diplomaRepository.delete(diploma);
        // Update User Status
        List<Diploma> diplomas = diplomaRepository.getAllByUser(user);
        boolean stillStudy = false ;

        // Chech if Still Study based On Graduation Date
        for (Diploma currDiploma :diplomas) {
            if (currDiploma.getGraduationDate()==null ){
                stillStudy = true  ;
                break;
            }
            else if(currDiploma.getGraduationDate().isAfter(LocalDate.now()) ) {
                stillStudy = true  ;
                break ;
            }
        }
        // Update User Status based On Status and if stillStudy
        if (user.getStatus()==null){
            if (stillStudy){
                user.setStatus("Study");
            }
        }
        else if(user.getStatus().equals("Study") && !stillStudy){
            user.setStatus(null);
//
        }
        else if (user.getStatus().equals("Work") && stillStudy ){
            user.setStatus("work_study");
        }
        else if (user.getStatus().equals("work_study") && !stillStudy ){
            user.setStatus("Work");
        }
        userRepository.save(user) ;
    }

    //
    public DiplomaResponseDTO getDiplomaDetails(Long diplomaId){
        Diploma diploma = diplomaRepository.findById(diplomaId).orElseThrow() ;
        return  new DiplomaResponseDTO().toDTO(diploma) ;
    }

   //
   public List<DiplomaResponseDTO>  getAllDiplomas(Long userId){
        User user = this.userRepository.findById(userId).orElseThrow() ;

        List<Diploma> diplomas = this.diplomaRepository.getAllByUser(user) ;
        List<DiplomaResponseDTO> response = new ArrayList<>() ;
       for (Diploma diploma:diplomas) {
           response.add(new DiplomaResponseDTO().toDTO(diploma)) ;
       }
        return  response ;
   }


   //************************************** Skill  ******************

    public NewSkillResponseDTO addSkill(Long userId , NewSkillRequestDTO data ){
        Skill skill = new Skill() ;
        User user = this.userRepository.findById(userId).orElseThrow() ;
        skill.setUser(user);
        skill.setName(data.getName());
        skill.setLevel(data.getLevel());
        this.skillRepository.save(skill) ;
        return new NewSkillResponseDTO().toDto(skill) ;
    }


    public List<NewSkillResponseDTO> getAllSkils(Long userId){
        User user = this.userRepository.findById(userId).orElseThrow();
        List<Skill> skills = this.skillRepository.findAllByUser(user) ;
        List<NewSkillResponseDTO> response = new ArrayList<>();
        for (Skill skill: skills) {
            response.add(new NewSkillResponseDTO().toDto(skill)) ;
        }
        return response ;
    }

    public  NewSkillResponseDTO getSkillById(Long skillId ){
        Skill skill = this.skillRepository.findById(skillId).orElseThrow() ;
        return new NewSkillResponseDTO().toDto(skill) ;
    }

    public NewSkillResponseDTO updateSkill(Long skillId , NewSkillRequestDTO data){
        Skill skill = this.skillRepository.findById(skillId).orElseThrow( );
        skill.setLevel(data.getLevel());
        this.skillRepository.save(skill) ;
        return  new NewSkillResponseDTO().toDto(skill) ;
    }

    public  void  deleteSkill(Long skillId ){
        this.skillRepository.deleteById(skillId);
    }
}
