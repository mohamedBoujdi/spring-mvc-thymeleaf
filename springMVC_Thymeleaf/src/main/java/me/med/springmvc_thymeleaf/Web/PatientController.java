package me.med.springmvc_thymeleaf.Web;

import lombok.AllArgsConstructor;
import me.med.springmvc_thymeleaf.Entities.Patient;
import me.med.springmvc_thymeleaf.Repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    //@Autowired //mais il n'est pas recommandé
    private PatientRepository patientRepository;

    /* ou il suffait d'utiliser @AllArg.... (LOMBOK annotations)
    public PatientController(PatientRepository patientRepository) {

        this.patientRepository = patientRepository;
    }*/

    @GetMapping(
            path = "/index"
    )
    public  String patients(                                              Model model,
                             @RequestParam(name = "page",defaultValue = "0") int page,
                             @RequestParam(name = "size",defaultValue = "5") int size,
                             @RequestParam(name = "keyword",defaultValue = "") String keyword
                           ){
       //List<Patient> patientList=patientRepository.findAll();
       // Page<Patient> pagePatient=patientRepository.findAll(PageRequest.of(page,size));
        Page<Patient> pagePatient=patientRepository.findByNameContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatients",pagePatient.getContent());
        model.addAttribute("pages",new int[pagePatient.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "listPatients";
    }
    @GetMapping("/delete")
    public String  delete(Long id,String keyword,int page){
        patientRepository.deleteById(id);
     return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public String  home(){
        return "redirect:/index";
    }
    @GetMapping("/patients")
    @ResponseBody  //pour ne pas change @controller to @RestController
    public List<Patient> listPatients(){
       return patientRepository.findAll();
    }
}
