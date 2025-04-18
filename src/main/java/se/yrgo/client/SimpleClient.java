package se.yrgo.client;

import se.yrgo.services.calls.CallHandlingService;
import se.yrgo.services.customers.CustomerManagementService;
import se.yrgo.services.customers.CustomerNotFoundException;
import se.yrgo.services.diary.DiaryManagementService;

import java.util.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.domain.*;

public class SimpleClient {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
        CustomerManagementService customerService = container.getBean(CustomerManagementService.class);
		DiaryManagementService diaryService = container.getBean(DiaryManagementService.class);

		CallHandlingService callService = container.getBean(CallHandlingService.class);

		Call newCall = new Call("Dom called from Twin Peaks Company");
		Action action1 = new Action ("Call back Dom as soon as possible for feedback",
									new GregorianCalendar(2019,12,10), "Mary");
		Action action2 = new Action ("Check if Dom called again",
							new GregorianCalendar(2019,12,11), "Mary");
		List<Action>actions = new ArrayList<Action>();
		actions.add(action1);
		actions.add(action2);

		try {
            Customer customer = customerService.findCustomerById("NV10");
			callService.recordCall(customer.getCustomerId(), newCall, actions);
		}catch(CustomerNotFoundException e) {
			System.err.println("This customer does not exist.");
		}

		System.out.println("Here are the actions:");
		Collection<Action>incompleteActions = diaryService.getAllIncompleteActions("Mary");
		for(Action action:incompleteActions) {
			System.out.println(action);
		}

        container.close();
    }
}