package PI.OLTP.API.Controller;

import PI.OLTP.API.DTO.ImageDTO;
import PI.OLTP.API.DTO.LocDto;
import PI.OLTP.API.DTO.RequestDtos.LoginRequestDTO;
import PI.OLTP.API.DTO.RequestDtos.SatisfactionLevel;
import PI.OLTP.API.DTO.RequestDtos.Skill.NewSkillRequestDTO;
import PI.OLTP.API.DTO.RequestDtos.account.newAccount.NewUserDTO;
import PI.OLTP.API.DTO.RequestDtos.account.update_account.UpdateAdminRequestDTO;
import PI.OLTP.API.DTO.RequestDtos.account.update_account.UpdateUserRequestDTO;
import PI.OLTP.API.DTO.RequestDtos.diploma.NewDiploma;
import PI.OLTP.API.DTO.RequestDtos.job.NewJob;
import PI.OLTP.API.DTO.ResponseDtos.AdminUniversity;
import PI.OLTP.API.DTO.RequestDtos.account.account.detailed.DetailedAccountDTO;
import PI.OLTP.API.DTO.RequestDtos.account.account.personal.AccountDTO;
import PI.OLTP.API.DTO.RequestDtos.account.account.personal.AdminDTO;
import PI.OLTP.API.DTO.RequestDtos.account.newAccount.NewAdminDTO;
import PI.OLTP.API.DTO.ResponseDtos.LoginResponseDTO;
import PI.OLTP.API.DTO.RequestDtos.account.account.personal.UserDTO;
import PI.OLTP.API.DTO.RequestDtos.account.account.undetailed.UndetailedAccountDTO;
import PI.OLTP.API.DTO.ResponseDtos.Skill.NewSkillResponseDTO;
import PI.OLTP.API.DTO.ResponseDtos.account.UpdateAdminResponseDTO;
import PI.OLTP.API.DTO.ResponseDtos.account.UpdateUserResponseDTO;
import PI.OLTP.API.DTO.ResponseDtos.diploma.DiplomaResponseDTO;
import PI.OLTP.API.DTO.ResponseDtos.job.PersonalJobDetailsDTO;
import PI.OLTP.API.DTO.ResponseDtos.job.UndetailedJobDTO;
import PI.OLTP.API.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("PI/OLTP/API")
@RestController
public class Controller {
    public final AdminService adminService;
    public final LoginService loginService;
    public final AccountService accountService;
    public final UserService userService;
    private final LocationService locationService ;
    @Autowired
    public Controller(AdminService adminService, LoginService loginService, AccountService accountService , UserService userService , LocationService locationService) {
        this.adminService = adminService;
        this.loginService = loginService;
        this.accountService = accountService;
        this.userService=userService ;
        this.locationService=locationService ;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO data) {
        return loginService.checkLogin(data);
    }

    @GetMapping("/unique-email/{email}")
    public boolean uniqueEmail(@PathVariable String email ){

        return  accountService.uniqueEmail(email)  ;
    }
    //
    @GetMapping("/unique-password/{passWord}")
    public boolean uniquePassword(@PathVariable String passWord ){
        return  accountService.uniquePassWord(passWord);
    }

    //
    @GetMapping("get-personal-account-details/{id}")
    public AccountDTO getPersonalAccountDetails(@PathVariable Long id) {
        return accountService.getPersonalAccountDetails(id);
    }

    //
    @GetMapping("/get-account-details-by-id/{id}")
    public DetailedAccountDTO getAccountDetailsById(@PathVariable Long id) {
        return accountService.getAccountDetailsById(id);
    }

    //
    @GetMapping("/get-locations")
    public  List<LocDto> getLocation(){

        return  this.locationService.getAllLocations() ;
    }

    @GetMapping("/get-all-accounts")
    public List<UndetailedAccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PutMapping(value = "/update-image/{accountId}" , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} )
    public ImageDTO  addAdmin(@PathVariable Long accountId , @RequestPart("imageFile")MultipartFile imageFile) {
        try{
//            return adminService.addAdmin(data , imageFile );
            return  accountService.updateImage(accountId , imageFile ) ;

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null ;
        }
    }
//////////////////////////////////////////// Admin ////////////////////////////////////////////////////////////////

    @DeleteMapping("/delete-account/{accountId}")
    public void deleteAccount(@PathVariable Long accountId){
        this.adminService.deleteAccount(accountId);
    }
    //
    @PutMapping("/update-admin-account/{id}")
    public UpdateAdminResponseDTO updateAdminAccount(@PathVariable Long id , @RequestBody @Valid UpdateAdminRequestDTO data) {
        return adminService.updatePersonalAccount(id , data ) ;
    }


    @PostMapping(value = "/add-admin" , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} )
    public AdminDTO addAdmin(@RequestPart("data") NewAdminDTO data , @RequestPart("imageFile")MultipartFile imageFile) {
        try{
            return adminService.addAdmin(data , imageFile );

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null ;
        }
    }

    @DeleteMapping("/delete-image/{accountId}")
    public void deleteImage(@PathVariable Long accountId ){
        this.accountService.deleteImage(accountId) ;
    }
    //
    @PostMapping(value= "/add-user" , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public UserDTO addUser(@RequestPart("data")NewUserDTO data , @RequestPart("imageFile") MultipartFile imageFile ) {
        try {
            return adminService.addUser(data , imageFile);

        }
        catch (Exception e ){
            System.out.println(e.getMessage());
            return null ;
        }
    }

    @GetMapping("/get-admin-university/{id}")
    public AdminUniversity getAdminUniversity(@PathVariable Long id ){
        return adminService.getAdminUniversity(id) ;
    }

    //////////////////////////////////////////// User  ////////////////////////////////////////////////////////////////

    @GetMapping("/get-stisfaction-level/{userId}")
    public int getSatisfactionLeve(@PathVariable Long userId ){
        return  this.userService.getSatisfactionLevel(userId ) ;
    }

    @PutMapping("/update-st-level")
    public int updateSatisfactionLevel(@RequestBody @Valid SatisfactionLevel stLevl ){

        return userService.updateSatisfactionLevel(stLevl) ;
    }
    @PutMapping("/update-user-account/{id}")
    public UpdateUserResponseDTO updateUserAccount(@PathVariable Long id , @RequestBody @Valid UpdateUserRequestDTO data) {
        return userService.updatePersonalAccount(id , data ) ;
    }



    // Job
    @PostMapping("/add-job/{userId}")
    public PersonalJobDetailsDTO addJob(@PathVariable Long userId , @RequestBody @Valid NewJob data ){
        return  userService.addJob(userId,data) ;
    }

    @PutMapping("/update-job/{jobId}")
    public PersonalJobDetailsDTO updateJob(@PathVariable Long jobId , @RequestBody @Valid NewJob data){
        return userService.updateJob(jobId,data) ;
    }

    @DeleteMapping("/delete-job/{jobId}")
    public  void deleteJob(@PathVariable Long jobId ){
        userService.deleteJob(jobId);
    }


    // Get All-Jobs
    // List<UndetailedJobDTO> getAllJobs(Long userId)
    @GetMapping("/get-all-jobs/{userId}")
    public List<UndetailedJobDTO> getAllJobs(@PathVariable Long userId ){
        return this.userService.getAllJobs(userId) ;
    }
    @GetMapping("/get-personal-job-details-by-job-id/{jobId}")
    public PersonalJobDetailsDTO getPersonalJobDetails(@PathVariable Long jobId ){
        return userService.getJobDetails(jobId) ;
    }
    //

    // Diploma
    @PostMapping("/add-diploma/{userId}")
    public DiplomaResponseDTO addDiploma(@PathVariable Long userId ,@RequestBody @Valid NewDiploma data ){
        return userService.addDiploma(userId,data) ;
    }

    @PutMapping("/update-diploma/{diplomaId}")
    public DiplomaResponseDTO updateDiploma(@PathVariable Long diplomaId , @RequestBody @Valid NewDiploma data){
        return  userService.updateDiploma(diplomaId,data) ;
    }

    @DeleteMapping("/delete-diploma/{diplomaId}")
    public void  deleteDiploma(@PathVariable  Long diplomaId ){
        userService.deleteDiploma(diplomaId);
    }

    @GetMapping("/get-all-diplomas/{userId}")
    public  List<DiplomaResponseDTO> getAllDiplomas(@PathVariable Long userId ){
        return  this.userService.getAllDiplomas(userId) ;
    }
    @GetMapping("/get-diploma-personal-details/{diplomaId}")
    public DiplomaResponseDTO getDiploma(@PathVariable Long diplomaId){
        return userService.getDiplomaDetails(diplomaId) ;
    }

   // Skill     ///////////////////////////

    @PostMapping("/add-skill/{userId}")
    public NewSkillResponseDTO addSkill(@PathVariable Long userId ,@RequestBody NewSkillRequestDTO data ){
        return this.userService.addSkill(userId , data ) ;
    }

    @GetMapping("/get-all-skills/{userId}")
    public List<NewSkillResponseDTO> getAllSkills(@PathVariable Long userId){
        return this.userService.getAllSkils(userId) ;
    }

    @GetMapping("/get-skill-by-id/{skillId}")
    public NewSkillResponseDTO getSkillById(@PathVariable Long skillId){
        return  this.userService.getSkillById(skillId) ;
    }

    @PutMapping("/update-skill/{skillId}")
    public  NewSkillResponseDTO updateSkill(@PathVariable Long skillId , @RequestBody NewSkillRequestDTO data ){
        return  this.userService.updateSkill(skillId,data ) ;
    }

    @DeleteMapping("/delete-skill/{skillId}")
    public void deleteSkill(@PathVariable  Long skillId ){
        this.userService.deleteSkill(skillId);
    }
 }