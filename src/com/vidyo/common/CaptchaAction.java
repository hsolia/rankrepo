package com.vidyo.common;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;


@FacesValidator("com.vidyo.common.CaptchaAction")
public class CaptchaAction implements Validator {

    public CaptchaAction() {
    }
    String validate;

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }


    
  

	@Override
	public void validate(FacesContext fc, UIComponent arg1, Object arg2)
			throws ValidatorException {
		// TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Boolean isResponseCorrect = Boolean.FALSE;
        javax.servlet.http.HttpSession session = request.getSession();
        String parm = (String)arg2;
        String c = (String) session.getAttribute(MyCaptcha.CAPTCHA_KEY);
        if (!parm.equals(c)) {
        	FacesMessage message = new FacesMessage();
        	message.setSummary("You have entered invalid text for image.");
        	message.setSeverity(FacesMessage.SEVERITY_ERROR);
        	throw new ValidatorException(message);

        }
	}
}