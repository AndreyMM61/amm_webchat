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
package ru.amm_company.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author mam
 */
public class InputPanel extends Panel {
	public InputPanel(String id) {
		super(id);		
		init();
	}
    
	
	private void init(){
		Form inputForm = new InputForm("inputForm");
		add(inputForm);
	}

        public class InputForm extends Form {
            
		private String textMessage;
//		private String textMessage;
                private TextArea message = new TextArea("message", Model.of(""));
                
        public InputForm(String id) {
			super(id);
			
		setDefaultModel(new CompoundPropertyModel(this));
//		message = new TextArea("message");	
/*		
		add(new Link("sendMessage"){

			@Override
			public void onClick() {
                                textMessage = "hello";
                        }
			
		});
*/
//                add(new Label("textMessage", "Hi")); 
                add(message);
                add(new Label("textMessage", "Hi"));
//                setRenderBodyOnly(true);
/*
                add(new Link("loginPage"){

			@Override
			public void onClick() {
				setResponsePage(LoginPage.class);
			}
		});
*/
	}

        public final void onSubmit() {
                textMessage = message.getInput();
        }			
    }
}
