package application.cargacuentas;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;

import exceptions.ArchivoConErroresException;
import model.Empresa;
import model.data.HandlerArchivoJSON;
import model.repositories.RepoEmpresas;

public class CargaCuentasEmpresas {

	private FTPClient ftp;

	private String rutaFTP = "CuentasEmpresas.json";

	private String ip = "jpbulbulian.brickftp.com";
	private String user = "grupo12";
	private String pass = "grupo12grupo12";

	public void conectar(String ip, String user, String pass) throws SocketException, IOException {
		ftp = new FTPClient();
		ftp.connect(ip);

		if (ftp.login(user, pass))
			System.out.println("login OK");
		else
			System.out.println("login Error");
	}

	public boolean descargar(File dest) throws IOException {
		FileOutputStream fos = new FileOutputStream(dest);
		boolean r;
		if (ftp.retrieveFile(rutaFTP, fos)){
			System.out.println("descarga OK");
			r = true;
		}
		else{
			System.out.println("descarga Error");
			r = false;
		}
		fos.close();
		return r;
	}

	private void desconectar() throws IOException {
		ftp.disconnect();
	}

	private void moveTo(String directory) throws IOException{
		if(ftp.cwd(directory) == 550)
			ftp.makeDirectory("/" + directory);
		else
			ftp.cwd("../");
		String[] newName = rutaFTP.split("\\.(?=[^\\.]+$)",2);
		newName[0] += ("-" + directory.toUpperCase() + "-" + LocalDateTime.now());
		ftp.rename(rutaFTP, directory + "/" + newName[0] + "." + newName[1]);
	}
	
	private void moveToProcessed() throws IOException{
		moveTo("Processed");
	}
	
	private void moveToError() throws IOException{
		moveTo("Error");
	}
	
	public void cargar(){
		try {
			conectar(ip, user, pass);
			if(Arrays.stream(ftp.listNames()).anyMatch(s -> s.equals(rutaFTP))){
				File tmpFile = File.createTempFile("CuentasEmpresas", ".json");
				if(descargar(tmpFile)){
					try{
						List<Empresa> empresas = new HandlerArchivoJSON(tmpFile.getAbsolutePath()).loadEmpresas();
						RepoEmpresas.getInstance().insertarVarios(empresas);
					}
					catch(ArchivoConErroresException e){
						moveToError();
					}
				}
				tmpFile.delete();
				moveToProcessed();
			}
//			ftp.rename("Processed/CuentasEmpresas-PROCESSED-2017-11-23T19:11:20.154.json", rutaFTP);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				desconectar();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
