package o.c.gj.controller;

import o.c.gj.cons.CustomConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

public class DustController {
    @Value("${common.front.nginx:http://127.0.0.1}")
    protected String env;

    @Value("${common.product:false}")
    protected boolean isProduct;

    @ModelAttribute
    public void addBaseModel(ModelMap modelMap) {
        modelMap.put(CustomConstant.Http.ENV, env);
        modelMap.put(CustomConstant.Http.PRODUCT, isProduct);
    }
}
