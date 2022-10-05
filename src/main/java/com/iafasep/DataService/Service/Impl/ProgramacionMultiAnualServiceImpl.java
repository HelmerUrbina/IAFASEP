/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.DataService.Service.Impl;

import com.google.gson.Gson;
import com.iafasep.BusinessServices.Beans.BeanProgramacionMultianual;
import com.iafasep.BusinessServices.Beans.BeanProgramacionMultianualDetalle;
import com.iafasep.BusinessServices.Beans.BeanProgramacionMultianualDetalleDto;
import com.iafasep.BusinessServices.Beans.BeanProgramacionMultianualDto;
import com.iafasep.DataService.DAO.ProgramacionMultiAnualDAO;
import com.iafasep.DataService.DAO.ProgramacionMultiAnualDetalleDao;
import com.iafasep.DataService.Service.ProgramacionMultiAnualService;
import com.iafasep.Utiles.Utiles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Service
public class ProgramacionMultiAnualServiceImpl implements ProgramacionMultiAnualService{
    
    @Autowired
    private ProgramacionMultiAnualDAO programacionMultiAnualDAO;
    
    @Autowired
    private ProgramacionMultiAnualDetalleDao programacionMultiAnualDetalleDao;
    
    @Override
    public List<BeanProgramacionMultianualDto> getProgramacionMultianual(Integer fuente, String periodo) {
        List<BeanProgramacionMultianualDto> response = new ArrayList<>();
        List<BeanProgramacionMultianual> list = programacionMultiAnualDAO.findAll(fuente, periodo);
//        System.out.println("list de dao -> " + new Gson().toJson(list));
        if(list.size() > 0){
            try {
                BeanProgramacionMultianualDto a = new BeanProgramacionMultianualDto();
                String tarea = "";
                Integer songIndex = 1;
                for(BeanProgramacionMultianual p : list){
                    if(!tarea.equals(p.getTareaCodigo())){
                        tarea = p.getTareaCodigo();
                        a.setTareaCodigo(tarea);
                        String [] x = p.getTarea().split(":");
                        a.setTarea(x[1]);
                        a.setProgracion1(p.getProgramado());
                        a.setDetalle1(p.getDetalle());
                        a.setSaldo1(p.getSaldo());
                        a.setMeta1(p.getMeta());
                        a.setEstado(p.getEstado());
                        a.setUbigeo(p.getUbigeoCodigo());
                        a.setDepartamento(p.getDepartamento());
                        a.setProvincia(p.getProvincia());
                        a.setDistrito(p.getDistrito());
                    }else{
                        if(songIndex%2 == 0){
                            a.setProgracion2(p.getProgramado());
                            a.setDetalle2(p.getDetalle());
                            a.setSaldo2(p.getSaldo());
                            a.setMeta2(p.getMeta());
                        }else{
                            a.setProgracion3(p.getProgramado());
                            a.setDetalle3(p.getDetalle());
                            a.setSaldo3(p.getSaldo());
                            a.setMeta3(p.getMeta());
                            response.add(a);
                            songIndex = 0;
//                            System.out.println("DTO -> " + new Gson().toJson(a));
                            a = new BeanProgramacionMultianualDto();
                        }
                        
                    }
                    songIndex++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        System.out.println("list de response -> " + new Gson().toJson(response));
        return response;
    }
    
    @Override
    public List<BeanProgramacionMultianualDetalleDto> getProgramacionMultianualDetalle(Integer fuente, String periodo, Integer tarea) {
        List<BeanProgramacionMultianualDetalleDto> response = new ArrayList<>();
        List<BeanProgramacionMultianualDetalle> list = programacionMultiAnualDetalleDao.findAll(fuente, periodo, tarea);
        System.out.println("list de dao -> " + new Gson().toJson(list));
        if(list.size() > 0){
            try {
                BeanProgramacionMultianualDetalleDto a = new BeanProgramacionMultianualDetalleDto();
                String clasificador = "";
                Integer songIndex = 1;
                for(BeanProgramacionMultianualDetalle p : list){
                    if(!clasificador.equals(p.getClasificadorCodigo())){
                        clasificador = p.getClasificadorCodigo();
                        a.setClasificadorCodigo(clasificador);
                        a.setClasificador(p.getClasificador());
                        a.setImporte1(p.getImporte());
                    }else{
                        if(songIndex%2 == 0){
                            a.setImporte2(p.getImporte());
                        }else{
                            a.setImporte3(p.getImporte());
                            response.add(a);
                            System.out.println("DTO -> " + new Gson().toJson(a));
                            songIndex = 0;
                            a = new BeanProgramacionMultianualDetalleDto();
                        }
                        
                    }
                    songIndex++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("list de response -> " + new Gson().toJson(response));
        return response;
    }
    
    @Override
    public String guardarProgramacionMulti(HashMap obj, String usuario, String modo) {
        String result = "GUARDO";
        try {
            programacionMultiAnualDAO.sp_progMultiAnual(
                                                        obj.get("periodo").toString(),
                                                        Integer.parseInt(obj.get("fuente").toString()),
                                                        Integer.parseInt(obj.get("tarea").toString()),
                                                        obj.get("ubigeo").toString(),
                                                        obj.get("cant").toString(),
                                                        usuario,
                                                        modo);
        } catch (Exception | Error e) {
            result = Utiles.getErrorSQL((Exception) e);
        }
        return result;
    }

    @Override
    public String guardarProgramacionMultiDetalle(HashMap obj, String usuario) {
        String result = "GUARDO";
        try {
            programacionMultiAnualDetalleDao.sp_progMultiAnualDetalle(
                                                        obj.get("periodo").toString(),
                                                        Integer.parseInt(obj.get("fuente").toString()),
                                                        Integer.parseInt(obj.get("tarea").toString()),
                                                        obj.get("cant").toString(),
                                                        usuario);
        } catch (Exception | Error e) {
            result = Utiles.getErrorSQL((Exception) e);
        }
        return result;
    }

}
