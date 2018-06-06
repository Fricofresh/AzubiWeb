package de.dpma.azubiweb.view.berichtsheft.panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.dpma.azubiweb.model.User;
import de.dpma.azubiweb.service.BerichtsheftService;
import de.dpma.azubiweb.util.BerichtsheftData;
import de.dpma.azubiweb.view.berichtsheft.Berichtsheft;
import de.dpma.azubiweb.view.berichtsheft.PanelChange;

/**
 * Panel zum Eingaben des Berichtsheftes für Montag bis Freitag
 * 
 * @author Benedikt Maier
 *
 */
public class AzubiDayPanel extends BerichtsheftPanel {
	public static final String NAME = "AzubiDay";
	private de.dpma.azubiweb.model.Berichtsheft report;
	private static final List<String> TYPES = Arrays.asList(new String[] { "Keine", "Urlaub", "Gleittag", "Krank" });

	public AzubiDayPanel(User currentUser, BerichtsheftService berichtsheftService, PanelChange change,
			de.dpma.azubiweb.model.Berichtsheft report) {

		super(currentUser, berichtsheftService, change, NAME);
		this.report = report;
		this.init();

	}

	private ArrayList<IModel<String>> textModel;

	public void init() {
		if (report == null && Berichtsheft.testID == 1) {
			report = createTest();
		}
		ArrayList<String> data = BerichtsheftData.getDataFromXML(report.getData());
		textModel = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			textModel.add(Model.of(data.get(i)));
		}
		List<String> listArt = Arrays.asList(de.dpma.azubiweb.model.Berichtsheft.kindOfBH);
		String selectedTyp = report.getKind_BHL();
		DropDownChoice<String> listSites = new DropDownChoice<String>("sites",
				new PropertyModel<String>(this, "selectedTyp"), listArt);
		Form<?> form = new Form<Void>("form") {
			@Override
			protected void onSubmit() {

				info("Selected search engine : " + selectedTyp);

			}
		};
		add(form);
		form.add(listSites);
		ListView<String> list = new ListView<String>("reportView", data) {
			@Override
			protected void populateItem(ListItem<String> item) {
				String tempRep = item.getModelObject();
				item.add(new Label("dayLabel", Berichtsheft.getStringForDay(item.getIndex())));
				TextArea<String> ta;
				CheckBox cbU;
				CheckBox cbF;
				CheckBox cbK;
				switch (tempRep) {
				case "Urlaub":
					ta = new TextArea<>("dayArea", Model.of(""));
					cbU = new CheckBox("uCb", Model.of(true));
					cbK = new CheckBox("kCb", Model.of(false));
					cbF = new CheckBox("fCb", Model.of(false));
					break;
				case "Krank":
					ta = new TextArea<>("dayArea", Model.of(""));
					cbU = new CheckBox("uCb", Model.of(false));
					cbK = new CheckBox("kCb", Model.of(true));
					cbF = new CheckBox("fCb", Model.of(false));
					break;
				case "Feiertag":
					ta = new TextArea<>("dayArea", Model.of(""));
					cbU = new CheckBox("uCb", Model.of(false));
					cbK = new CheckBox("kCb", Model.of(false));
					cbF = new CheckBox("fCb", Model.of(true));
					break;
				default:
					ta = new TextArea<>("dayArea", textModel.get(item.getIndex()));
					cbU = new CheckBox("uCb", Model.of(false));
					cbK = new CheckBox("kCb", Model.of(false));
					cbF = new CheckBox("fCb", Model.of(false));
					break;
				}
				item.add(ta);
				item.add(cbU);
				item.add(cbF);
				item.add(cbK);

			}
		};
		this.add(list);
		Button saveB = new Button("saveB") {
			@Override
			public void onSubmit() {
				info("signB.onSubmit executed");
				signPressed();
			}
		};
		this.add(saveB);
		Button signB = new Button("signB") {
			@Override
			public void onSubmit() {
				info("signB.onSubmit executed");
				signPressed();
			}
		};
		this.add(signB);

	}



	private void savePressed() {
		String[] sText = new String[textModel.size()];
		for (int i = 0; i < sText.length; i++) {
			sText[i] = textModel.get(i).getObject();
		}

		report.setData(BerichtsheftData.getXMLFromData(sText, report.getWeekAYear() % 100));
		service.saveBerichtsheft(report);
	}

	private void signPressed() {
		String[] sText = new String[textModel.size()];
		for (int i = 0; i < sText.length; i++) {
			sText[i] = textModel.get(i).getObject();
		}

		report.setData(BerichtsheftData.getXMLFromData(sText, report.getWeekAYear() % 100));
		report.setStatus_submit(true);
		service.saveBerichtsheft(report);
	}
	
	/**
	 * Erstellen des Testcases
	 * @return
	 */
	private de.dpma.azubiweb.model.Berichtsheft createTest() {
		de.dpma.azubiweb.model.Berichtsheft report = new de.dpma.azubiweb.model.Berichtsheft(currentUser,
				de.dpma.azubiweb.model.Berichtsheft.kindOfBH[0], 201822);
		report.setData(BerichtsheftData.getXMLFromData(
				new String[] { "MontagDaten", "DienstagDaten", "MittwochDaten", "DonnerDaten", "Gleittag" }, 22));
		return report;
	}
}
