package de.dpma.azubiweb.view.berichtsheft.panel;

import java.util.ArrayList;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.dpma.azubiweb.model.Rolle;
import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.BerichtsheftService;
import de.dpma.azubiweb.util.BerichtsheftData;
import de.dpma.azubiweb.view.berichtsheft.Berichtsheft;
import de.dpma.azubiweb.view.berichtsheft.PanelChange;

/**
 * Panel zum Abzeichnen des BerichtheftesMa!
 * 
 * @author Benedikt Maier
 *
 */
public class SignPanel extends BerichtsheftPanel {
	public static final String NAME = "Sign";
	private de.dpma.azubiweb.model.Berichtsheft report;
	private IModel<String> erMod;

	public SignPanel(String id, User currentUser, BerichtsheftService berichtsheftService, PanelChange change,
			de.dpma.azubiweb.model.Berichtsheft report) {

		super(id, currentUser, berichtsheftService, change, NAME);
		this.report = report;
		this.init();

	}

	public void init() {
		ArrayList<String> data = BerichtsheftData.getDataFromXML(report.getData());
		if (data == null) {
			data = new ArrayList<>();
			for (int i = 0; i < 5; i++) {
				data.add("TestTestTestTestTestTestTestTestTestTest");
			}
		}
		ArrayList<IModel<String>> textModel = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			textModel.add(Model.of(data.get(i)));
		}
		System.out.println("model,size: " + textModel.size());
		ListView<String> list = new ListView<String>("reportView", data) {

			@Override
			protected void populateItem(ListItem<String> item) {
				String tempRep = item.getModelObject();
				info(item.getIndex());
				item.add(new Label("dayLabel", Berichtsheft.getStringForDay(item.getIndex())));
				TextArea<String> ta;
				switch (tempRep) {
				case "Urlaub":
					ta = new TextArea<>("dayArea", Model.of(""));
					item.add(new CheckBox("uCb", Model.of(true)));
					item.add(new CheckBox("kCb", Model.of(false)));
					item.add(new CheckBox("fCb", Model.of(false)));
					break;
				case "Krank":
					ta = new TextArea<>("dayArea", Model.of(""));
					item.add(new CheckBox("uCb", Model.of(false)));
					item.add(new CheckBox("kCb", Model.of(true)));
					item.add(new CheckBox("fCb", Model.of(false)));
					break;
				case "Feiertag":
					ta = new TextArea<>("dayArea", Model.of(""));
					item.add(new CheckBox("uCb", Model.of(false)));
					item.add(new CheckBox("kCb", Model.of(false)));
					item.add(new CheckBox("fCb", Model.of(true)));
					break;
				default:
					ta = new TextArea<>("dayArea", textModel.get(item.getIndex()));
					item.add(new CheckBox("uCb", Model.of(false)));
					item.add(new CheckBox("kCb", Model.of(false)));
					item.add(new CheckBox("fCb", Model.of(false)));
					break;
				}
				ta.setEnabled(false);
				item.add(ta);

			}
		};

		this.add(list);

		this.add(new Label("errorLabel", Model.of("Fehlerbenachrichtigung")));
		erMod = Model.of("");
		TextArea<String> errorArea = new TextArea<>("errorArea");
		this.add(errorArea);

		Button signB = new Button("signB") {
			@Override
			public void onSubmit() {
				info("signB.onSubmit executed");
				signPressed();
			}
		};
		Button errorB = new Button("errorB") {
			@Override
			public void onSubmit() {
				info("errorB.onSubmit executed");
				errorPressed();
			}
		};
		this.add(signB);

		this.add(errorB);

	}

	private void errorPressed() {
		if (erMod.getObject() != null) {
			report.setErrorMessage(erMod.getObject());
			service.saveBerichtsheft(report);
			report.setSt_sign_AB(false);
			report.setSt_sign_AL(false);
			change.backPressed();
		} else {
			error("Keine Fehlerbenachrichitgung angegeben!");
		}

	}

	private void signPressed() {
		if (currentUser.getRolle().getId() == Rolle.Beschreibung.AL.getRolleId()) {
			report.setSt_sign_AL(true);
			change.changeToOverviewAzubiList();
		} else {
			report.setSt_sign_AB(true);
			change.changeToOverviewAzubiList();
		}

	}

}
