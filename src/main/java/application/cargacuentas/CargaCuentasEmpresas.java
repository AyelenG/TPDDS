package application.cargacuentas;

import java.io.BufferedOutputStream;
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

	private static String rutaLocal = "data/CuentasEmpresas.json";
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

	public static void descargarArchivoFTP(String localFile, String hostFile)
			throws FileNotFoundException, IOException {
		// fos = new FileOutputStream(localFile);
		BufferedOutputStream buffOut = new BufferedOutputStream(new FileOutputStream(localFile));
		if (ftp.retrieveFile(hostFile, buffOut))
			System.out.println("Descarga correcta");
		else
			System.out.println("Error Descarga");

		buffOut.close();
		// fos.close();
	}

	private static void desconectar() throws IOException {
		ftp.disconnect();
	}

	public void cargar(){
		try {
			conectar(ip, user, pass);
			descargarArchivoFTP(rutaLocal, rutaFTP);
			List<Empresa> empresas = new HandlerArchivoJSON(rutaLocal).loadEmpresas();
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
