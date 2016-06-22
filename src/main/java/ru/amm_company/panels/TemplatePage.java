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
package ru.amm_company.panels;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

/**
 *
 * @author mam
 */
public class TemplatePage extends WebPage {
	public static final String CONTENT_ID = "contentComponent";

 	private Component headerPanel;
	private Component menuPanel;
	private Component footerPanel;

        public TemplatePage() {

		add(headerPanel = new HeaderPanel("headerPanel"));
		add(menuPanel = new MenuPanel("menuPanel"));
		add(footerPanel = new FooterPanel("footerPanel"));
		add(new Label(CONTENT_ID, "This is test programm \"amm_webchat\""));
        }

//getters for layout areas
	protected Component getHeaderPanel() {
		return headerPanel;
	}

	protected Component getMenuPanel() {
		return menuPanel;
	}

	protected Component getFooterPanel() {
		return footerPanel;
	}

}
