package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.CuentaPeriodo;
import model.Empresa;

import model.Periodo;
import model.repositories.RepoEmpresas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EmpresasController {

	public static ModelAndView handleEmpresas(Request req, Response res) {
		Map<String, List<Empresa>> model = new HashMap<>();
		model.put("empresas", RepoEmpresas.getInstance().findAll());
		return new ModelAndView(model, "empresas/empresas.hbs");
	}
	public static ModelAndView handleSeleccionPeriodo(Request req, Response res){
		Map<String, Object> model = new HashMap<>();
		String periodo = req.queryParams("periodo");
		Empresa empresa = RepoEmpresas.getInstance().get(Long.parseLong(req.params("empresa")));
		
		if(periodo!=null){
			res.redirect(req.pathInfo() +"/" + periodo);
		}
		model.put("periodos", empresa.getPeriodos());
		model.put("empresa", empresa);
		return new ModelAndView(model, "empresas/selector-periodo.hbs");
		
	}
	public static ModelAndView handleCuentas(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();
		Empresa empresa = RepoEmpresas.getInstance().get(Long.parseLong(req.params("empresa")));
		Integer anio = Integer.valueOf(req.params("periodo"));
		Periodo periodo = empresa.buscarPeriodo(new Periodo(anio));
		List<CuentaPeriodo> cuentas = periodo.getCuentas();
		
		model.put("cuentas", cuentas);
		model.put("empresa", empresa);
		model.put("anio", anio);
		return new ModelAndView(model,  "empresas/cuentas.hbs");
	}
	
}
