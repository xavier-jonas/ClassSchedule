/**
 * 
 */
package gui;


import gui_schedule.FrameSchedule;
import gui_selection.FrameSelection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.StateFullSchedule;

/**
 * @author Dubruille Xavier
 * @author Delange Jonas 
 *
 */
public class MyMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	FrameSchedule fSc;
	FrameSelection fSe;
	

	/**
	 * 
	 * @param state
	 */
	public MyMenuBar(final StateFullSchedule state) {

		
		//empty panel; has to be updated later
		//fSc= new  FrameSchedule(); 
		//fSe= new FrameSelection();

		// * top menus
		// * declarations
		JMenu file =new JMenu("Fichier");
		JMenu edit =new JMenu("Edition");
		JMenu help =new JMenu("Aide");
		// * end of declarations

		// ** file 
		// ** declarations
		JMenuItem new_project = new JMenuItem ("Nouveau");
		JMenuItem open = new JMenuItem ("Ouvrir");
		JMenuItem save = new JMenuItem ("Enregistrer");
		JMenuItem quit = new JMenuItem ("Quitter");
		JMenuItem about = new JMenuItem ("About");
		// ** end of declarations

		// ** save
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(state==null || !state.isReady())
					return;
				JFileChooser jf=new JFileChooser();
				
				int ret=jf.showSaveDialog((JComponent)e.getSource());
				if(ret == JFileChooser.APPROVE_OPTION)
					state.saveProject(jf.getSelectedFile().getPath());
				
			}
		});
		// ** end of save
		
		// ** new_project
		new_project.addActionListener(new CsvActionListener(state));
		// ** end of new_project

		// ** quit
		quit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				// il faudrait gerer la sauvegarde automatique ici
				System.exit(0);
			}
		});
		// ** end of quit
		
		// ** open
		open.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null,  "<html>Cette option n'est malheureusement pas encore pleinement fonctionnelle. <br>" +
						"Veuillez nous en excuser.</html>", "Sorry",JOptionPane.WARNING_MESSAGE); 
			}
		});
		// ** end of open

		file.add(new_project);
		file.add(open);
		file.add(save);
		file.add(quit);
		// ** end of file

		// * help
		// ** about
		about.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				JOptionPane.showMessageDialog(null,  "<html>Programme de gestion et creation d'horaire. <br><br>" +
						"R�alis� dans le cadre du cour de <i>Langage avanc� de programmation</i> de Mm. Vroman, <br>" +
						"pour l'Ephec. <br><br>" +
						"Sous licence GPL2.<br><br>" +
						"Auteurs: xavier.dubruille@gmail.com et jonas.delange@gmail.com <br>" +
						"N'h�sitez pas � nous contacter! </html>", 
						"About",JOptionPane.INFORMATION_MESSAGE); 
				
			}
		});
		// ** end of about
		
		help.add(about);
		// * end of help
		
		add(file);
		add(edit);
		add(help);
		// * end of top menus
	}

	/**
	 * 
	 * @param fSe
	 * @param fSc
	 */
	public void setPanels(FrameSelection fSe,FrameSchedule fSc){
		this.fSc=fSc;
		this.fSe=fSe;
	}


	/**
	 * This listener will take care of the "new" item..
	 */
	private class CsvActionListener implements ActionListener{
		StateFullSchedule state;


		public CsvActionListener(StateFullSchedule state){
			this.state=state;
		}

		@Override
		public void actionPerformed(ActionEvent ae){
			
			boolean reset=state.isReady();

			if(reset){	
				state.filesPath=new String[4];
				
				System.out.println("et on recommence..");
				
				state.reset();
				fSc.clear();
				fSe.clear();
			}
			
			//GetFilesDialog dialog=
			new GetFilesDialog(state.getFilesPath());
			
			if(reset){	

			}
			
			
			boolean IsUpdatedWrite=state.update_from_files();
			if(!IsUpdatedWrite && reset)
				System.out.println("Strange state? .. we have to fix that ?..");
			
			if(IsUpdatedWrite){ //update the model; i.e. the internal data


				
				fSc.update_from_state(); //update the GUI
				fSe.update_from_state(); //update the GUI
			}
		}
	}

}
