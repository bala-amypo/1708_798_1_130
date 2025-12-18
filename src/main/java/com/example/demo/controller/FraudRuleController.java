// package com.example.demo.controller;
// import com.example.demo.entity.FraudRule;
// import com.example.demo.service.FraudRuleService;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/fraud-rules")
// @Tag(name = "Fraud Rules")
// public class FraudRuleController {

//     private final FraudRuleService fraudRuleService;

//     public FraudRuleController(FraudRuleService fraudRuleService) {
//         this.fraudRuleService = fraudRuleService;
//     }

//     @PostMapping
//     public FraudRule createRule(@RequestBody FraudRule rule) {
//         return fraudRuleService.createRule(rule);
//     }

//     @PutMapping("/{id}")
//     public FraudRule updateRule(
//             @PathVariable Long id,
//             @RequestBody FraudRule rule) {
//         return fraudRuleService.updateRule(id, rule);
//     }

//     @GetMapping("/active")
//     public List<FraudRule> getActiveRules() {
//         return fraudRuleService.getActiveRules();
//     }

//     @GetMapping("/{id}")
//     public FraudRule getById(@PathVariable Long id) {
//         return fraudRuleService.getAllRules()
//                 .stream()
//                 .filter(r -> r.getId().equals(id))
//                 .findFirst()
//                 .orElseThrow();
//     }

//     @GetMapping
//     public List<FraudRule> getAllRules() {
//         return fraudRuleService.getAllRules();
//     }
// }
