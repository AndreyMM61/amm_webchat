/*
 * Copyright 2016 mam.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.amm_company;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.AttributeModifier;
import ru.amm_company.pages.ChatPage;

/**
 *
 * @author mam
 */
public class LoginPanel extends Panel {
	public LoginPanel(String id) {
		super(id);		
		init();
	}
    
	
	private void init(){
		Form loginForm = new LoginForm("loginForm");
		add(loginForm);
	}
	
	public class LoginForm extends Form{
		private String username;
		private String password;
		private String loginStatus;
                
                private Label ls;
		
		public LoginForm(String id) {
			super(id);
			
			setDefaultModel(new CompoundPropertyModel(this));
			
			add(new TextField("username"));
			add(new PasswordTextField("password"));
                        ls = new Label("loginStatus"); 
			add(ls);
		}

		public final void onSubmit() {			
			if(username.equals("test") && password.equals("test")) {
				loginStatus = "Congratulations!";
                                ls.add(AttributeModifier.replace("style", "color:green;font-weight:bold"));
				setResponsePage(ChatPage.class);
                        }
                        else {
				loginStatus = "Wrong username or password !";			
                                ls.add(AttributeModifier.replace("style", "color:red;font-weight:bold"));
                        }
		}
	}
}
