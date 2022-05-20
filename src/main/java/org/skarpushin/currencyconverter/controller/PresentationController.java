package org.skarpushin.currencyconverter.controller;

import org.skarpushin.currencyconverter.errorhandler.BadRequestException;
import org.skarpushin.currencyconverter.model.ConvertIntput;
import org.skarpushin.currencyconverter.model.SymbolsResponse;
import org.skarpushin.currencyconverter.thirdparty.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

import static org.skarpushin.currencyconverter.thirdparty.Constants.CONVERT_URI;

@Controller
public class PresentationController {

    @Autowired
    private CurrencyService currencyService;
    private Map<String, String> currenciesCache;

    @GetMapping("/")
    public String homePage(Model model) {
        if (currenciesCache == null) {
            // fill the cache using external api
            SymbolsResponse symbols = currencyService.getCurrencies();
            if (symbols.isSuccess()) {
                currenciesCache = symbols.getSymbols();
            } else {
                throw new BadRequestException("Can't get symbols from external API");
            }
        }
        model.addAttribute("convertInput", new ConvertIntput());
        model.addAttribute("symbols", currenciesCache);
        return "index";
    }

    @PostMapping("/")
    public String convertSubmit(@ModelAttribute ConvertIntput convertInput, Model model) {
        return "redirect:/" + String.format("convert?from=%s&to=%s&monetaryValue=%s", convertInput.getFrom(), convertInput.getTo(), convertInput.getAmount());
    }
}
