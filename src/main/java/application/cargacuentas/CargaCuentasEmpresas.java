package application.cargacuentas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;

import model.Empresa;
import model.data.HandlerArchivoJSON;
import model.repositories.RepoEmpresas;

public class CargaCuentasEmpresas {

	private static FTPClient ftp;

	private static String rutaFTP = "/CuentasEmpresas.json";

	private static String ip = "jpbulbulian.brickftp.com";
	private static String user = "grupo12";
	private static String pass = "grupo12grupo12";

	public static void conectar(String ip, String user, String pass) throws SocketException, IOException {
		ftp = new FTPClient();
		ftp.connect(ip);

		if (ftp.login(user, pass))
			System.out.println("login OK");
		else
			System.out.println("login Error");
	}

	public static File descargarArchivoFTP(String hostFile)
			throws FileNotFoundException, IOException {
		File tmpFile = File.createTempFile("CuentasEmpresas", ".json");
		FileOutputStream fos = new FileOutputStream(tmpFile);
		if (ftp.retrieveFile(hostFile, fos))
			System.out.println("Descarga correcta");
		else
			System.out.println("Error Descarga");
		fos.close();
		return tmpFile;
	}

	private static void desconectar() throws IOException {
		ftp.disconnect();
	}

	public void cargar(){
		try {
			conectar(ip, user, pass);
			File tmpFile = descargarArchivoFTP(rutaFTP);
			List<Empresa> empresas = new HandlerArchivoJSON(tmpFile.getAbsolutePath()).loadEmpresas();
			tmpFile.delete();
			RepoEmpresas.getInstance().insertarVarios(empresas);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				desconectar();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
