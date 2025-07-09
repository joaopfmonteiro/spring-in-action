package com.example.sie.taco_cloud.web;

import com.example.sie.taco_cloud.Ingredient;
import com.example.sie.taco_cloud.Taco;
import com.example.sie.taco_cloud.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.example.sie.taco_cloud.Ingredient.Type;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j //a Lombok-provided annotation that, at compilation time, will automatically generate an SLF4J (Simple Logging Facade for Java)
@Controller
// The @RequestMapping annotation is used to map HTTP requests to specific handler methods in a controller.
// It can be applied at the class or method level. When used on a class, it defines a base URI for all methods in the class.
// When used on a method, it maps a specific HTTP request path and optionally HTTP methods (GET, POST, etc.)
@RequestMapping("/design")
// The @SessionAttributes("tacoOrder") annotation tells Spring MVC to store the "tacoOrder"
// object in the HTTP session, rather than just in the model for a single request.
// This is useful when you need to maintain state (like a multi-step form)
// across multiple requests or pages. In this case, the tacoOrder object
// is created during the taco design step (/design), and it needs to persist
// while the user enters delivery information and confirms the order
// on other pages (/orders/current and /orders/submit).
// Without this annotation, the tacoOrder would be lost between requests.
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    @ModelAttribute
    public void addIngredientsToModel (Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        Type [] types = Ingredient.Type.values();
        for (Type type : types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type){
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors erros, @ModelAttribute TacoOrder tacoOrder) { //@Valid triggers automatic validation of an object based on its Bean Validation annotations (e.g., @NotBlank, @Size).
        if(erros.hasErrors()){
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }
}
