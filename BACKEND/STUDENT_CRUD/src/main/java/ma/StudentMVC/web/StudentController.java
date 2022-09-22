package ma.StudentMVC.web;

import lombok.AllArgsConstructor;
import ma.StudentMVC.entities.Student;
import ma.StudentMVC.repositories.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {
    private StudentRepository StudentRepository;

    @GetMapping(path = "/")
    public String index(Model model){
        return "redirect:/user/listeS";
    }
    @GetMapping(path = "/user/listeS")
    public String Students(Model model,
                           @RequestParam(name = "page",defaultValue ="0") int page,
                           @RequestParam(name="size",defaultValue ="5") int size,
                           @RequestParam(name="keyword",defaultValue ="") String keyword) {
        Page<Student> pageStudents = StudentRepository.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listStudents",pageStudents.getContent());
        model.addAttribute("pages",new int[pageStudents.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "students";
    }

    @GetMapping(path = "/deleteStudent")
    public String delete(Long id,String keyword,int page){
        StudentRepository.deleteById(id);
        return "redirect:/user/listeS?page="+page+"&keyword="+keyword;
    }


    @GetMapping("/Students")
    @ResponseBody
    public List<Student> listStudents(){
        return StudentRepository.findAll();
    }

    @GetMapping("/formStudent")
    public String formStudent(Model model){
        model.addAttribute("student",new Student());
        return "formStudents";
    }

    @PostMapping("/saveStudent")
    public String save(Model model, @Valid Student Student, BindingResult bindingResult,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "formStudents";
        StudentRepository.save(Student);
        return "redirect:/user/listeS?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/editStudent")
    public String editStudent(Model model,Long id,String keyword,int page){
        Student Student=StudentRepository.findById(id).orElse(null);
        if(Student==null) throw new RuntimeException("Student introuvable");
        model.addAttribute("student",Student);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "editStudents";
    }
}
