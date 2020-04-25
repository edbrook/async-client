package uk.co.edbrook.async.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;
import uk.co.edbrook.async.domain.Subscribe;
import uk.co.edbrook.async.domain.SubscriptionService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SubscribeWebController {

    private final SubscriptionService service;

    @GetMapping
    public String homePage(@RequestParam(required = false) String subId) {
        return "home";
    }

    @GetMapping("/subscribe")
    public String subscribe(Subscribe subscribe) {
        return "subscribe";
    }

    @PostMapping("/subscribe")
    public DeferredResult<String> subscribe(@Valid Subscribe subscribe, BindingResult bindingResult) {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        if (bindingResult.hasErrors()) {
            log.error("Binding Error: {}", bindingResult);
            deferredResult.setResult("subscribe");
        } else {
            log.info("Subscribe with: {}", subscribe);
            service.subscribe(subscribe, deferredResult);
        }
        return deferredResult;
    }
}
