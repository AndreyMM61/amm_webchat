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

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 *
 * @author mam
 */
public class MessagePanel extends Panel {
	public MessagePanel(String id) {
		super(id);		
		init();
	}
    
	
	private void init(){
		Form messageForm = new MessageForm("messageForm");
		add(messageForm);
	}
	
        public class MessageForm extends Form {
            
//		private String message;
//		private String textMessage;

        public MessageForm(String id) {
			super(id);
			
			setDefaultModel(new CompoundPropertyModel(this));
        }
    }               
}
