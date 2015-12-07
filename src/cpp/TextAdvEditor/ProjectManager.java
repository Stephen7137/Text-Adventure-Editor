package cpp.TextAdvEditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

import javax.swing.JFileChooser;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Saves and loads {@link ProjectManager#editor} from a file {@link #file}.
 * If {@link #file} is null then {@link #saveAs()} gets the file name from
 * from the user.
 * @author Stephen
 *
 */
public class ProjectManager {
	
	ChapterEditor editor;
	FileChooser fileChooser;
	File file;
	
	/**
	 * Creates the project {@link #editor} and sets the location and file type of the
	 * {@link #fileChooser}. The starting location for {@link #fileChooser} is users
	 * home file.
	 */
	public ProjectManager(){
		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(
				new FileChooser.ExtensionFilter("Project file", "*.project"));
		//fileChooser.setInitialDirectory(JFileChooser());
	}
	
	/**
	 * Checks to see if {@link #file} is null, if not then calls {@link #saveChapter()}
	 * If file is not null then {@link #editor} has been saved before or has been
	 * loaded from a file.
	 */
	public void save(){
		//if(editor.chapter.getFileName().equals("")){
		//	GetChapterName();
		//	fileChooser.setInitialFileName(editor.chapter.getFileName());
		//}
		if(file == null){
			saveAs();
		}else{
			saveChapter();
		}
	}
	
	private void GetChapterName() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Ask the user for location of where to save the project and saves the location
	 * to {@link #file} for later uses.
	 */
	public void saveAs(){
		file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
        	saveChapter();
        }
	}
	
	/**
	 * Creates SaveProject and saves the object to a file with the file name
	 * the same as the Chapter title.
	 */
	private void saveChapter(){
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			SaveProject save = new SaveProject(editor);
			oos.writeObject(save);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a new empty project. If the project is not empty then
	 * call {@link #eraseData()} checks to see if the old project will
	 * overwrite with a new project.
	 * @return true if a new project was created.
	 */
	public boolean newProject(){
		//if(!editor.isEmpty()){
		//	if(eraseData()) return false;
		//}
		//editor = new ChapterEditor("");
		return true;
	}
	
	private boolean eraseData(){
		return false;
	}
	
	public ChapterEditor getChapter(){
		return editor;
	}
}
