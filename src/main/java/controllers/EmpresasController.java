package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Cuenta;
import model.CuentaPeriodo;
import model.Empresa;
import model.Metodologia;
import model.Periodo;
import model.repositories.RepoEmpresas;
import model.repositories.RepoMetodologias;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EmpresasController {

	public static ModelAndView handleEmpresas(Request req, Response res) {
		Map<String, List<Empresa>> model = new HashMap<>();
		model.put("empresas", RepoEmpresas.getInstance().findAll());
		return new ModelAndView(model, "empresa/empresas.hbs");
	}
	/*public static ModelAndView handleCuentas(Request req, Response res) {
		Map<String, List<Periodo>> model = new HashMap<>();
		Empresa empresaElegida = RepoEmpresas.getInstance().get(Long.parseLong(req.params("id")));
		List<Periodo> periodos = empresaElegida.getPeriodos();
		model.put("periodos", periodos);
		return new ModelAndView(model,  "empresa/cuentas.hbs");
	}
	*/
}
