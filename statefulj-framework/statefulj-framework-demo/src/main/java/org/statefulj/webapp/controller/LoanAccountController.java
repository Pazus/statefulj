package org.statefulj.webapp.controller;

import javax.annotation.Resource;

import org.statefulj.framework.core.annotations.StatefulController;
import org.statefulj.framework.core.annotations.Transition;
import org.statefulj.webapp.form.AccountForm;
import org.statefulj.webapp.model.Account;
import org.statefulj.webapp.model.LoanAccount;
import static org.statefulj.webapp.model.LoanAccount.*;
import org.statefulj.webapp.services.AccountService;

@StatefulController(
	clazz=LoanAccount.class,
	startState=NON_EXISTENT,
	factoryId="accountService"
)
public class LoanAccountController {
	
	@Resource
	AccountService accountService;
	
	@Transition(from=NON_EXISTENT, event="springmvc:post:/accounts/loan", to=APPROVAL_PENDING)
	public String createAccount(Account account, String event, AccountForm form) {
		account.setAmount(form.getAmount());
		return "redirect:/user";
	}
}