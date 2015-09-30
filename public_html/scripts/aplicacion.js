//Evaluar como extraer los ids de ast desde el propiod objeto y en otros casos que aplique
var apl = {
    ast: {
		dats: {
			atDocs: {
				eleA: $('[data-at=atDocs]'),
				eleB: $($('[data-nat=atDocs]').parent()),
				id: 'atDocs',
				tex: 'Documentos'
			},
			atGras: {
				eleA: $('[data-at=atGras]'),
				eleB: $($('[data-nat=atGras]').parent()),
				id: 'atGras',
				tex: 'Gráfico'
			},
			atInf: {
				eleA: $('[data-at=atInf]'),
				eleB: $($('[data-nat=atInf]').parent()),
				id: 'atInf',
				tex: 'Información'
			},
			atMaps: {
				eleA: $('[data-at=atMaps]'),
				eleB: $($('[data-nat=atMaps]').parent()),
				id: 'atMaps',
				mPpl: {
					acsgs: {
						e: false,
						eleC: $('#mask-layer-tree-geographic-map-main-list'),
						eleE: $('#wrapper-layer-tree-geographic-map-main')
					},
					al: 'map-main',
					ctrls: {},
					eleA: $('#map-main'),
					eves: {
						cr: false
					}
				},
				tex: 'Mapa'
			},
			atTabs: {
				eleA: $('[data-at=atTabs]'),
				eleB: $($('[data-nat=atTabs]').parent()),
				id: 'atTabs',
				tex: 'Tabla'
			}
		}
    },
	conf: {},
	cs: {
		dats: {},
		datsFisGens: {
			dats: {
				ens: {
					dats : {
						deps: {
							hjs: {idEns: ['muns']},
							funs: {
								oeh: function obtenerEnsHijas(idEnPa, indEnsHijas, arrIdsEnsHijasFis) {
									var resp = [], objEnsHijas = apl.dats.ens.dats[apl.dats.ens.dats.deps.hjs.idEns[indEnsHijas]].dats;
									if (!vd(arrIdsEnsHijasFis)) {
										arrIdsEnsHijasFis = Object.keys(objEnsHijas);
									}
									for (var id in objEnsHijas) {
										switch (indEnsHijas) {
											case 0:
												if (id.substring(0, 2) === idEnPa && arrIdsEnsHijasFis.indexOf(id) > -1) {
													if (!vd(objEnsHijas[id].id)) {
														objEnsHijas[id].id = id;
													}
													resp.push(objEnsHijas[id]);
												}
												break;
										}
									}
									return resp;
								}
							},
							nomFi: 'Por departamento:',
							tFi: 'select'
						},
						muns: {
							pas: {
								idEns: ['deps']
							},
							funs: {
								oep: function obtenerEntidadPa(indEnPa, idEn) {
									var resp = null, objEnsDats = apl.dats.ens.dats, idEnPa = idEn.substring(0, 2);
									switch (indEnPa) {
										case 0: 
											resp = objEnsDats[objEnDats.muns.pas.idEns[indEnPa]].dats[idEnPa];
											if (!vd(resp.id)) {
												resp.id = idEnPa;
											}
											break;
									}
									return resp;
								}
							},
							nomFi: 'Por municipio:',
							tFi: 'select'
						},
						nal: {
							nomFi: 'Nacional'
						},
						regis: {
							nomFi: 'Por región:',
							tFi: 'select'
						},
						terrs: {
							nomFi: 'Por área de territorio:',
							tFi: 'select'
						}
					},
					nomFi: 'Filtros por entidades territoriales',
					subTFi: 'radio',
					tFi: 'input'
				},
				pers: {
					dats: {
						anios: {
							nomFi: 'Año(s):',
							subTFi: 'checkbox',
							tFi: 'input',
							tFiPa: null
						},
						meses: {
							dats: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
							nomFi: 'Por mes(es):',
							subTFi: 'checkbox',
							tFi: 'input'
						},
						semestres: {
							dats: ['Enero a Junio', 'Julio a Diciembre'],
							nomFi: 'Por semestre(s):',
							subTFi: 'checkbox',
							tFi: 'input'
						},
						trimestres: {
							dats: ['Enero a Marzo', 'Abril a Junio', 'Julio a Septiembre', 'Octubre a Diciembre'],
							nomFi: 'Por trimestre(s):',
							subTFi: 'checkbox',
							tFi: 'input'
						}
					},
					nomFi: 'Filtros por periodos',
					subTFi: 'radio',
					tFi: 'input'
				},
				ots: {
					nomFi: 'Filtros generales',
					subTFi: 'radio',
					tFi: 'input'
				}
			},
			nomFi: 'Selección de filtros de consulta'
		},
		mscs: {
			mcsPpl: {
				eleE: $('#wrapper-query-menu-content'),
				nivs: [],
				ops: {}
			}
		}
	},
	dats: {
		csgs: {
			dats: {},
			nosCsgs: {},
			ol: []
		},
		ens: {
			dats: {}
		},
		ssgs: {
			dats: {}
		}
	},
    funs: {
        concat: function concatenar() {
            var resp = String(arguments[0]);
			for (var ind = 1; ind < arguments.length; ind = ind + 1) {
				resp = resp.concat(arguments[ind]);
			}
			return resp;
        },
        vd: function verificarDato(dat) {
            var resp = true;
            if (dat === undefined || dat === null) {
                resp = false;
            }
            return resp;
        }
    },
    logs: {
        adv: [],
        err: [],
		errF: [],
        men: []
    },
    propsGens: {
        dims: [[17, 'px'], [14, 'px'], [11, 'px'], [16, 'px'], [20, 'px']],
        durs: [500, 0]
    }
};

$(function() {
	'use strict';

    //<Variables>
    //De aplicación
    var id = null, ind = null, bPrimeraCarga = false;
	//De mapa
	//[0.0439453125, 0.02197265625, 0.010986328125, 0.0054931640625, 0.00274658203125, 0.001373291015625, 6.866455078125E-4, 3.4332275390625E-4, 1.71661376953125E-4, 8.58306884765625E-5, 4.291534423828125E-5, 2.1457672119140625E-5, 1.0728836059570312E-5, 5.364418029785156E-6, 2.682209014892578E-6, 1.341104507446289E-6, 6.705522537231445E-7, 3.3527612686157227E-7];
	var arrEsc = ["1:14'000.000 aprox.", "7'000.000 aprox.", "3'500.000 aprox.", "1'750.000 aprox.", "870.000 aprox.", "435.000 aprox.", "215.000 aprox.", "110.000 aprox."]; //, 55000, 27000, 13000, 7000, 3500, 1700, 900, 500, 250, 100],
    //De funciones
    var concat = apl.funs.concat, vd = apl.funs.vd;
	//De consultas
	var objFisSels = null;
	//</Variables>
	
	//try dentro de subfunciones como en caso de eventos dentro del handler
	//Clasificación dats para todos los elementos hijo de un objeto
	//mandar todos los afee a ge < generarEventos
	//Si no hay ninguna capa desde la carga inicial dl visor que elimine el botón mapa de la navegación
	//Restricted extent para el Mapa
    
    //<Expresiones de función/>
    //Generales
	var ccc = function conmutarClaseCss(ele, ccssRemover, ccssAgregar) {
		ele.addClass(ccssAgregar).removeClass(ccssRemover);
		return ele;
	};

	var ce = function crearElemento(nomNodo, atrs, ccsss, estilos) {
		var resp = document.createElement(nomNodo), raizId = 'generated-element-';
		if (vd(atrs.id) && !atrs.id.contains(raizId)) {
			atrs.id = concat(raizId, atrs.id);
		}
		return $(resp).addClass(ccsss).attr(atrs).css(estilos);
	};

    var esA = function esArreglo(dat) {
        return Array.prototype.isPrototypeOf(dat); 
    };

    var esF = function esFuncion(dat) {
        return Function.prototype.isPrototypeOf(dat);
    };

    var esN = function esNumero(dat) {
        return !isNaN(dat) && typeof dat == 'number' ? true : false; 
    };

    /*var esO = function esObjeto(d) {
        return Object.prototype.isPrototypeOf(d) && !esF(d) ?  true : false;
    };*/

    var esOp = function esObjetoPlano(dat) {
        return $.isPlainObject(dat);
    };

    var lf = function llamarFuncion() {
        var resp = null, args = arguments, dats = null;
        if (esA(args[0])) { 
            args = args[0]; 
        }
        dats = $(args).toArray();
        if (esF(dats.splice(0,1)[0])) {
            resp = args[0].apply(null, dats);
        } 
        return resp;
    };

    var rl = function registrarLog(t, tex, desc, complemento) {
		var arr = [new Date(), tex, desc];
		//Extraer el csgs, parte que falló por completo 
		if (t === 'errF-c') {
			t = 'err';
			//Tomar una acción falló una consulta
		} else if (t === 'errF-csgs') {
			t = 'err';
			//Tomar una acción, no hay ninguna capa cargada
		} 
		if (vd(complemento)) {
			arr.push(complemento);
		}
		apl.logs[t].push(arr);
		if (t === 'errF') {
			//Mostrar pantalla de carga con mensaje de error y apagar todo
		}

		//<Para valores únicos>
		//Forma A: posibles problemas de filter para todos los navegadores, onlyUnique por que toma los valores
		/*function onlyUnique(value, index, self) {
			onsole.log(value, index, self);
			return self.indexOf(value) === index;
		}
		var a = apl.logs.err;
		var unique = a.filter( onlyUnique );
		onsole.log(unique)*/
		
		//Forma B
		/*Array.prototype.getUnique = function(){
		   var u = {}, a = [];
		   for(var i = 0, l = this.length; i < l; ++i){
			  if(u.hasOwnProperty(this[i])) {
				 continue;
			  }
			  a.push(this[i]);
			  u[this[i]] = 1;
		   }
		   return a;
		}*/
		//</Para valores únicos>
    };

    var rp = function realizarPeticion(descPeticion, url, paramsPeticion, funExito, funErr) {
		var dats = {};
        try {
			funExito.push(dats);
			lf(funExito);
		} catch(err) {
			rl('err', concat('Realizando la solicitud >', descPeticion, '<'), 'Sucedió un error posterior a la recepción de la resp exitosa de una solicitud', {args: [url, paramsPeticion, funExito, funErr], men: err.message});
			lf(funErr);
		}
    };

	var rp_pru = function realizarPeticion_pru(descPeticion, url, paramsPeticion, funExito, funErr) {
		var dats = {};
        $.ajax({
			cache: false,
			contentType: "application/json; charset=windows-1252", //application/json; charset=windows-1252
			crossDomain: true,
			asinc: false,
            data: paramsPeticion, 
            dataType: 'json', 
			type: 'GET',
			url: url,
            success: function (dats) {
				try {
					console.log(dats);
					funExito.push(dats.resp);
					lf(funExito);
				} catch(err) {
					rl('err', concat('Realizando la solicitud >', descPeticion, '<'), 'Sucedió un error posterior a la recepción de la resp exitosa de una solicitud', {args: [url, paramsPeticion, funExito, funErr], men: err.message});
					lf(funErr);
				}
            }, error: function (resp, texErr) {
                var texLog = concat('Ah ocurrido un error al realizar la petición >', descPeticion, '<'), descLog = null;
                if (resp.status == 0) {
                    descLog = 'Al parecer el equipo está desconectado de la red';
                } else if (resp.status == 404) {
                    descLog = 'La URl especificada no ha dado resp';
                } else if (resp.status == 500) {
                    descLog = 'Error interno del servidor';
                } else if (texErr == 'parsererror') {
                    descLog = 'Error en la conversión de los datos';
                } else if (texErr == 'timeout') {
                    descLog = 'La petición excedió el tiempo máximo de espera permitido';
                } else { 
                    descLog = 'Error no identificado';
                }
				console.log(arguments);
				rl('err', texLog, descLog, {args: [url, paramsPeticion, funExito, funErr], men: texErr});
                lf(funErr);
            }
        });
    };

	/*var uosps = function unirObjetosPlanos(o1, o2) {
		for (id in o2) {
			o1[id] = o2[id];
		}
	};*/

    //Específicas
	var acggr = function anexarCapaGeograficaAGrupo(alM, datsCg) {
		var alCg = datsCg.al, objGrcsgs = apl.ast.dats.atMaps[alM].acsgs[datsCg.alGrcsgs], objAtrscg = null, contador = 0,
			nombresAtributos = ['Configuración', 'Información', 'Leyenda'], indNombreAtributo = 0, bAtrInformacion = false, leyenda = null;
		try {
			objGrcsgs.nscsgs[alCg] = {
				atrs: {
					alCg: alCg,
					conf: {
						eleA: ce('li', {}, 'w100', {}),
						eleCa: ce('div', {}, 'generated-element-attribute-geographic-layer-header w100', {}),
						eleCu: ce('div', {}, 'generated-element-attribute-geographic-layer-body w100', {display: 'none'})
					},
					eleC: ce('ul', {}, 'lsn w100', {}),
					inf: {
						eleA: ce('li', {}, 'w100', {}),
						eleCa: ce('div', {}, 'generated-element-attribute-geographic-layer-header w100', {}),
						eleCu: ce('div', {}, 'generated-element-attribute-geographic-layer-body w100', {display: 'none'})
					},
					le: {
						eleA: ce('li', {}, 'w100', {}),
						eleCa: ce('div', {}, 'generated-element-attribute-geographic-layer-header w100', {}),
						eleCu: ce('div', {}, 'generated-element-attribute-geographic-layer-body w100', {display: 'none'})
					}
				},
				eleA: ce('li', {}, 'w100', {}),
				eleCa: ce('div', {}, 'generated-element-node-geographic-layer-header w100', {}),
				eleCu: ce('div', {}, 'generated-element-node-geographic-layer-body w100', {})
			};
			objAtrscg = objGrcsgs.nscsgs[alCg].atrs;
			objGrcsgs.nscsgs[alCg].eleCa.append(
				ce('p', {'data-placement': 'top', title: datsCg.inf.nom}, 'b0 c0 cup dib generated-element-node-geographic-layer-header-text ma t0', {}).html(datsCg.inf.nom)
			);
			objGrcsgs.nscsgs[alCg].eleCa.append(
				ce('img', {alt: 'Mostrar/ocultar capa geográfica', src: datsCg.es.vi ? 'images/layerTreeGeographic/layer-hide.png' : 'images/layerTreeGeographic/layer-show.png'}, 'c1 cup dib generated-element-node-geographic-layer-header-button', {})
			);
			$.each(objAtrscg,
				function (id, val) {
					if (objAtrscg.hasOwnProperty(id) && esOp(val) && id.indexOf('al') === -1 && id.indexOf('ele') === -1) {
						val.eleCa.append(ce('img', {alt: 'Mostrar/ocultar área', src: 'images/layerTreeGeographic/attribute-show.png', 'data-alatrcg': id}, 'cup di generated-element-attribute-geographic-layer-toggle-button', {}));
						val.eleCa.append(ce('p', {}, 'b0 c0 di ma t0', {}).html(nombresAtributos[indNombreAtributo]));
						val.eleA.append(val.eleCa);
						val.eleA.append(val.eleCu);
						val.es = {d: false};
						indNombreAtributo = indNombreAtributo + 1;
					}
				}
			);
			if (vd(datsCg.inf.esc)) {
				objAtrscg.inf.eleCu.append(ce('p', {}, 'fwb generated-element-attribute-geographic-layer-subtitle', {}).html('Escala:'));
				objAtrscg.inf.eleCu.append(ce('p', {}, 'generated-element-attribute-geographic-layer-value', {}).html(datsCg.inf.esc));
				bAtrInformacion = true;
			}
			if (vd(datsCg.inf.anio)) {
				objAtrscg.inf.eleCu.append(ce('p', {}, 'fwb generated-element-attribute-geographic-layer-subtitle', {}).html('Año:'));
				objAtrscg.inf.eleCu.append(ce('p', {}, 'generated-element-attribute-geographic-layer-value', {}).html(datsCg.inf.anio));
				bAtrInformacion = true;
			}
			if (vd(datsCg.inf.fu)) {
				objAtrscg.inf.eleCu.append(ce('p', {}, 'fwb generated-element-attribute-geographic-layer-subtitle', {}).html('Fuente:'));
				objAtrscg.inf.eleCu.append(ce('p', {}, 'generated-element-attribute-geographic-layer-value', {}).html(datsCg.inf.fu));
				bAtrInformacion = true;
			}
			if (vd(datsCg.inf.desc)) {
				objAtrscg.inf.eleCu.append(ce('p', {}, 'fwb generated-element-attribute-geographic-layer-subtitle', {}).html('Descripción:'));
				objAtrscg.inf.eleCu.append(ce('p', {}, 'generated-element-attribute-geographic-layer-value', {}).html(datsCg.inf.desc));
				bAtrInformacion = true;
			}
			if (bAtrInformacion) {
				objAtrscg.eleC.append(objAtrscg.inf.eleA);
			}
			
			//Método para crear los ranges con su respectivo título
			objAtrscg.conf.eleCu.append(ce('p', {}, 'fwb generated-element-attribute-geographic-layer-subtitle', {}).html('Opacidad:'));
			objAtrscg.conf.eleCu.append(
				ce('input', {type: 'range', min: 0, max: 1, step: 0.1, val: datsCg.conf.opa, 'data-pccg': 'opa'}, 'generated-element-attribute-geographic-layer-range w100', {})
			);
			if (apl.conf.webGL) {
				objAtrscg.conf.eleCu.append(ce('p', {}, 'fwb generated-element-attribute-geographic-layer-subtitle', {}).html('Brillo:'));
				objAtrscg.conf.eleCu.append(
					ce('input', {type: 'range', min: -1, max: 1, step: 0.125, val: datsCg.conf.bri, 'data-pccg': 'bri'}, 'generated-element-attribute-geographic-layer-range w100', {})
				);
				objAtrscg.conf.eleCu.append(ce('p', {}, 'fwb generated-element-attribute-geographic-layer-subtitle', {}).html('Contraste:'));
				objAtrscg.conf.eleCu.append(
					ce('input', {type: 'range', min: -1, max: 1, step: 0.125, val: datsCg.conf.contr, 'data-pccg': 'contr'}, 'generated-element-attribute-geographic-layer-range w100', {})
				);
				
				//los demas ranges

			}
			objAtrscg.eleC.append(objAtrscg.conf.eleA);
			objGrcsgs.nscsgs[alCg].eleCu.append(objAtrscg.eleC);
			objGrcsgs.nscsgs[alCg].eleA.append(objGrcsgs.nscsgs[alCg].eleCa);
			objGrcsgs.nscsgs[alCg].eleA.append(objGrcsgs.nscsgs[alCg].eleCu);
			objGrcsgs.eleC.append(objGrcsgs.nscsgs[alCg].eleA);
			objGrcsgs.lon = objGrcsgs.lon + 1;
			ge(['ncg'], objGrcsgs.nscsgs[alCg]);
			objGrcsgs.nscsgs[alCg].es = {desp: true};
			objGrcsgs.nscsgs[alCg].e = 1;
			if (!objGrcsgs.e) {
				apl.ast.dats.atMaps[alM].acsgs.eleC.append(objGrcsgs.eleA);
				ge(['gcg'], objGrcsgs);
				objGrcsgs.es = {desp: true};
				objGrcsgs.e = true;
			}
			if (evoa(datsCg, 'cg', ['es', 'caLe'])) {
				leyenda = new Image();
				leyenda.src = concat(apl.dats.ssgs.dats[datsCg.alSg].conf.url, 'REQUEST=GetLegendGraphic&LAYER=', datsCg.conf.nomCg, '&FORMAT=image/png&VERSION=', apl.dats.ssgs.dats[datsCg.alSg].conf.verWms);
				$(leyenda).attr({'data-t': 'le'});
				objAtrscg.le.eleCu.append(leyenda);
				leyenda.onload = function (arg) {
					objAtrscg.eleC.append(apl.ast.dats.atMaps[alM].acsgs[datsCg.alGrcsgs].nscsgs[alCg].atrs.le.eleA);
					ge(['acg'], objAtrscg);
				};
				leyenda.onerror = function () {
					//<Verificar que funcione para todos la parte de leyenda.src>
					rl('err', concat('Cargando la leyenda gráfica de la capa geográfica de nombre ', datsCg.inf.nom, 'y de alias >', alCg, '<'), 'Ocurrió un error durante la carga de la leyenda gráfica de una capa geográfica', leyenda.src);
					//</Verificar>
					ge(['acg'], objAtrscg);
				};
			} else {
				ge(['acg'], objAtrscg);
			}
			ecsgsvr(alM, alCg);
		}  catch(err) {
			rl('err', concat('Anexando capa geográfica >', alCg, '< al grupo del árbol de capas del mapa >', alM, '<'), 'Mientras se anexaba un capa a un grupo de el árbol de capas de un mapa sucedió una excepción', err.message);
		}
	};

	var acgm = function agregarCapasGeograficasAMapa(alM, datsCsgs) {
		var mapa = apl.ast.dats.atMaps[alM], servicio = null, capasOl = [], capaOl = null, raizEvento = null, origenCg = null, objCg = null;
		try {
			for (id in datsCsgs) {
				if (datsCsgs.hasOwnProperty(id)) {
					objCg = apl.dats.csgs.dats[id] = datsCsgs[id];
					apl.dats.csgs.nosCsgs[objCg.conf.nomCg] = {al: id};
					servicio = apl.dats.ssgs.dats[objCg.alSg];
					if (vd(servicio)) {
						objCg.e = true;
						evosa(servicio, 'sg', [['conf', 'tServSg'], ['conf', 'verWms']]); 
						evosa(objCg, 'cg', [['conf', 'tCg'], ['conf', 'reMa'], ['conf', 'reMi'], ['es', 'vi'], ['conf', 'opa']]);
						if(!esA(evoa(objCg, 'cg', ['conf', 'lims']))) {
							objCg.conf.lims = objCg.conf.lims.split(',').map(parseFloat);
						}
						objCg.es.act = null;
						origenCg = {
							url: servicio.conf.url,
							params: {'LAYERS': objCg.conf.nomCg},
							serverType: servicio.conf.tServSg.toLowerCase()
						};
						objCg.conf.tCg = objCg.conf.tCg.toUpperCase();
						if (objCg.conf.tCg === 'WMS.IMAGE') {
							raizEvento = 'image';
							capaOl = new ol.layer.Image({
								source: new ol.source.ImageWMS(origenCg)
							});
						} else if (objCg.conf.tCg === 'WMS.TILE') {
							raizEvento = 'tile';
							capaOl = new ol.layer.Tile({
								source: new ol.source.TileWMS(origenCg)
							});
						}
						//var num1 = 0, num2 = 0;
						//Pasar estos eventos a la función var = ge
						capaOl.getSource().on(concat(raizEvento, 'loadstart'), function(event) {
							//onsole.log('n1:', num1++);
						});
						capaOl.getSource().on(concat(raizEvento, 'loadend'), function(event) {
							/*onsole.log('n2:', num2++);
							if (num1 === num2) {
							}*/
						});
						capaOl.getSource().on(concat(raizEvento, 'loaderror'), function(event) {
							var alCg = apl.dats.csgs.nosCsgs[this.getParams()['LAYERS']].al;
							apl.dats.csgs.dats[alCg].e = false;
							//<Ojo con alM: verificar que se cumpla para todos>
							//Se está cometiendo un error en esta solicitud
							//Que solo remueva la capa 1 vez 
							mapa.ol.removeLayer(apl.dats.csgs.dats[alCg].ol);
							//</Ojo con este alM>
							rl('err', concat('Capa: >', alCg, '<'), 'Error del servicio, puede deberse a malas especificaciones de los parámetros de configuración o puede tratarse de problemas con el acceso al servidor de mapas.');
							if (mapa.ol.getLayers().getArray().length === 0) {
								rl('errF-csgs', 'No fue posible cargar ninguna capa sobre el visor geográfico', 'Puede deberse a errores de comunicación, verifique su conexión a Internet');
							}
							if (apl.dats.csgs.dats[alCg].alGrcsgs) {
								rcgac(alM, apl.dats.csgs.dats[alCg].alGrcsgs, alCg);
							}
						});
						objCg.ol = capaOl;
						objCg.or = capasOl.length;
						capaOl.setExtent(objCg.conf.lims);
						capaOl.setOpacity(objCg.conf.opa);
						capaOl.setVisible(objCg.es.vi);
						if (apl.conf.webGL) {
							evosa(objCg, 'cg', [['conf', 'bri'], ['conf', 'contr']]);
							capaOl.setBrightness(objCg.conf.bri);
							capaOl.setContrast(objCg.conf.contr);
						}
						capasOl.push(objCg.ol);
						mapa.ol.addLayer(capaOl);
						if (vd(mapa.ctrls.visGen)) {
							if (objCg.es.viVisGen) {
								mapa.ctrls.visGen.getMap().addLayer(objCg.ol);
							}
						}
						if (vd(objCg.alGrcsgs) && vd(mapa.acsgs)) {
							objCg.al = id;
							acggr(alM, objCg);
						}
					} else {
						rl('err', concat('Servicio: >', objCg.alSg, '<'), 'Algo salió mal al acceder al objeto de un servicio, puede deberse a que el alias del servicio esté mal especificado');
						objCg.e = false;
					}
				}
			}
			apl.dats.csgs.ol = apl.dats.csgs.ol.concat(capasOl);
		}  catch(err) {
			rl('err', concat('Agregando capa geográfica >', id, '< al mapa >', alM, '<'), 'Mientras se generaba la tabla de contenido ocurrió una excepción', err.message);
		}
	};

	var agacg = function anexarGrupoAlArbolDeCapasGeograficas(alM, datsAcsgs) {
		var contador = 0, objGrcsgs = null, resp = true;
		try {
			for (id in datsAcsgs) {
				if (datsAcsgs.hasOwnProperty(id)) {
					objGrcsgs = apl.ast.dats.atMaps[alM].acsgs[id] = datsAcsgs[id];
					objGrcsgs.nscsgs = {};
					objGrcsgs.lon = 0;
					objGrcsgs.or = contador;
					objGrcsgs.eleA = ce('li', {'data-fs': 2}, 'w100', {'font-size': '11px'});
					objGrcsgs.eleCa = ce('div', {}, 'generated-element-group-geographic-layer-header tac w100', {}).append(
						ce('p', {}, 'b0 c0 cup generated-element-group-geographic-layer-header-text ma t0 ttu', {}).html(objGrcsgs.inf.nom)
					);
					objGrcsgs.eleCu = ce('div', {}, 'generated-element-group-geographic-layer-body w100', {});
					if (vd(objGrcsgs.inf.desc)) {
						objGrcsgs.eleCu.append(
							ce('p', {}, '', {}).html(objGrcsgs.inf.desc)
						);
					}
					objGrcsgs.eleC = ce('ul', {}, 'lsn', {});
					objGrcsgs.eleCu.append(objGrcsgs.eleC);
					objGrcsgs.eleA.append(objGrcsgs.eleCa);
					objGrcsgs.eleA.append(objGrcsgs.eleCu);
					evoa(objGrcsgs, 'grcsgs', ['inf', 'img']);
					objGrcsgs.e = false;
					contador = contador + 1;
				}
			}
			apl.ast.dats.atMaps.mPpl.acsgs.e = true;
		} catch(err) {
			rl('err', 'Generando los elementos del árbol de capas geográficas', 'Mientras se generaba el árbol de capas geográficas ocurrió una excepción', err.message);
			apl.ast.dats.atMaps.mPpl.acsgs.e = false;
			racsm(alM);
			resp = false;
		}
		return resp;
	};

    var aei = function ajustarElementosDeInterfaz () {
        $('#wrapper-options').width(concat($('#branding-image').width(), 'px'));
		$('#wrapper-query-menu-content').css('top', concat($('#wrapper-menu').position().top + $('#wrapper-menu').height() - 2, 'px'));
        cds();
    };

	var bp = function bloquearPantalla(indCCssFondo, esAnim, fun) {
		var elePantallaBloqueo = $('#lock-screen'), durAnim = apl.propsGens.durs[0];
		switch (indCCssFondo) {
			case 0:
				ccc(elePantallaBloqueo, 'lock-screen-background-color-1', 'lock-screen-background-color-0');
				break;
			case 1:
				ccc(elePantallaBloqueo, 'lock-screen-background-color-0', 'lock-screen-background-color-1');
				break;
		}
		if (!esAnim) {
			durAnim = 1;
		}
		elePantallaBloqueo.css({display: 'block'}).animate(
			{
				opacity: 1
			},
			durAnim,
			function () {
				if (esF(fun)) {
					lf(fun);
				}
			}
		);
	};

	var cds = function configurarDimensiones(arrCds) {
        var indI = null, indJ = null, datas = [['fs', 'font-size'], ['h', 'height'], ['lh', 'line-height'], ['w', 'width']],
			eleB = $('#layer-tree-geographic-map-main-toggle-button');
        if (vd(arrCds)) {
            apl.propsGens.dims = arrCds;
        } else {
            $('#wrapper-sizes p').each(function (ind, ele) {
                    apl.propsGens.dims[ind] = [$(ele).height(), 'px'];
            });
        }
        for (indI in apl.propsGens.dims) {
            for (indJ in datas) {
				$(concat('[data-', datas[indJ][0], '="', indI, '"]')).css(datas[indJ][1], concat(apl.propsGens.dims[indI][0], apl.propsGens.dims[indI][1]));
            }
        }
		eleB.css('width', concat(eleB.parent().height() + 1, 'px'));
		$('#wrapper-branding-text').css('height', concat(apl.propsGens.dims[0][0] * 2 + 12, apl.propsGens.dims[0][1]));
        $('#wrapper-breadcrumb-content').css('height', concat(apl.propsGens.dims[2][0] + 5, apl.propsGens.dims[2][1]));
        $('#wrapper-navigation-content').css('height', concat($('#wrapper-tools').width() * 5, 'px'));
		$('.generated-element-attribute-geographic-layer-subtitle').css('font-size', concat(apl.propsGens.dims[2][0] - 1, apl.propsGens.dims[2][1]));
    };

	var cm = function crearMapa(alM, controles) {
		var mapa = apl.ast.dats.atMaps[alM], mapaOl = null, 
		objMapa = {
			//controls: ol.control.defaults().extend([
				
			//]),
			interactions: ol.interaction.defaults().extend([
				new ol.interaction.DragRotateAndZoom()
			]),
			target: apl.ast.dats.atMaps[alM].al,
			view: new ol.View({
				center: [-72, 4],
				projection: 'EPSG:4326',
				resolutions: [0.0439453125, 0.02197265625, 0.010986328125, 0.0054931640625, 0.00274658203125, 0.001373291015625, 6.866455078125E-4, 3.4332275390625E-4],
				zoom: 0
			})
		};
		if (ol.has.WEBGL) {
			//objMapa.renderer = 'webgl';
			//apl.conf.webGL = true;
		}
		if (!vd(controles)) {
			controles = {dz: true, le: true, visGen: true};
		}
		mapaOl = new ol.Map(
			objMapa
		);
		if (controles.dz) {
			mapaOl.addControl(mapa.ctrls.dz = new ol.control.ZoomSlider());
		}
		if (controles.le) {
			mapaOl.addControl(mapa.ctrls.le = new ol.control.ScaleLine());
		}
		if (controles.visGen) {
			mapaOl.addControl(
				mapa.ctrls.visGen = new ol.control.OverviewMap({
					collapsed: false,
					view: new ol.View({
						projection: 'EPSG:4326'
					})
				})
			);
		}
		mapa.ol = mapaOl;
		ge(['m'], alM);
		return mapaOl;
	};

	var cnmcp = function configurarNivelDeMenuDeConsultasPrincipal(indNiv, idOp, tex, eleB) {
		var objNiv = null, bCoincidencias = false, objMcsPpl = apl.cs.mscs.mcsPpl;
		if (!vd(idOp)) {
			idOp = null;
		}
		if (objMcsPpl.nivs.length > indNiv) {
			for (ind = indNiv; ind < objMcsPpl.nivs.length; ind = ind + 1) {
				objNiv = objMcsPpl.nivs[ind];
				objNiv.eleE.css('display', 'none');
				//<OJO Modificar este borrado por algo más practico, incluso eliminarlo>
				objNiv.eleE.find('> div > p').html('');
				//</OJO>
				objNiv.eleC.empty();
				objNiv.eleE.find('> div > p').html(tex);
			}
			objNiv = objMcsPpl.nivs[indNiv];
			$.each(objNiv.ops,
				function (id, val) {
					if (val.alOpPa === idOp) {
						objNiv.eleC.append(val.eleC);
						bCoincidencias = true;
					}
				}
			);
			if (bCoincidencias) {
				objNiv.eleE.css('display', 'block');
			}
		}
		if (!bCoincidencias) {
			if (vd(eleB)) {
				afee(eleB, 'mouseleave', 
					function (arg) {
						$(arg.target).removeClass('generated-element-option-query-menu-text-selected');
					}
				);
			}
		}
	}

    var dbas = function deseleccionarBotonDeAreaDeTrabajoSeleccionada() {
        if (vd(apl.ast.sel)) {
            ccc(apl.ast.dats[apl.ast.sel].eleB.find('div:nth-child(2)'), 'bc3', 'bc4');
            ccc(apl.ast.dats[apl.ast.sel].eleB.find('div:nth-child(1)'), 'element-navigation-description-selected', 'element-navigation-description');
        }
    };

	var dp = function desbloquearPantalla(esAnim, fun) {
		var elePantallaBloqueo = $('#lock-screen'), durAnim = apl.propsGens.durs[0] * 2;
		if (!esAnim) {
			durAnim = 1;
		}
		elePantallaBloqueo.animate(
			{
				opacity: 0
			},
			durAnim,
			function () {
				elePantallaBloqueo.css({display: 'none'})
				if (esF(fun)) {
					lf(fun);
				}
			}
		);
	};

	var ecsgsvr = function establecerCapasGeograficasVisiblesPorResolucion(alM, alCg) {
		var resolucionActual = apl.ast.dats.atMaps[alM].ol.getView().getResolution(), mapa = apl.ast.dats.atMaps[alM];
		if (vd(alCg)) {
			ecgvr(apl.dats.csgs.dats[alCg]);
		} else {
			$.each(apl.dats.csgs.dats, 
				function (id, val) {
					if (apl.dats.csgs.dats.hasOwnProperty(id)) {
						ecgvr(val);
					}
				}
			);
		}
		function ecgvr(cg) {
			var objCg = apl.ast.dats.atMaps[alM].acsgs[cg.alGrcsgs].nscsgs[cg.al], boton = objCg.eleCa.find('.generated-element-node-geographic-layer-header-button');
			//Estudiar optimización especialmente de if (!cg.es.vi) { ya que es casi igual que en el evento
			if (resolucionActual >= cg.conf.reMa && resolucionActual <= cg.conf.reMi) {
				ccc(objCg.eleCa, 'generated-element-node-geographic-layer-header-disabled', 'generated-element-node-geographic-layer-header-enabled');
				cg.ol.setVisible(cg.es.vi);
				if (!cg.es.vi) {
					boton.attr('src', boton.attr('src').replace('hide', 'show'));
				} else {
					boton.attr('src', boton.attr('src').replace('show', 'hide'));
				}
				cg.es.act = true;
			} else {
				ccc(objCg.eleCa, 'generated-element-node-geographic-layer-header-enabled', 'generated-element-node-geographic-layer-header-disabled');
				cg.ol.setVisible(false);
				boton.attr('src', boton.attr('src').replace('hide', 'show'));
				cg.es.act = false;
			}
		}
	};

	var evoa = function evaluarValorDeObjetoDeAplicacion(obj, nomPlantilla, atrs) {
		var resp = null,
			plantillaCg = {acc: 0, conf: {bri: 0, contr: 0, fis: null, formato: 'image/png', lims: [-85.023, -7.228, -66.847, 15.167], opa: 1, reMa: 0.00034332275390625, reMi: 0.0439453125, sire: 'EPGS:4326', tCg: 'WMS.IMAGE'}, es: {autoIden: false, caLe: true, iden: true, or: true, transp: true, viVisGen: false, vi: true}, inf: {anio: null, desc: null, esc: null, fu: null, img: 'images/layersGeographic/default.png', nom: null, palsCla: null}, t: 1},
			plantillaGrcsgs = {inf: {desc: null, img: 'images/layerTreeGeographic/groups/default.png', pa: null}},
			plantillaSg = {acc: 0, conf: {tServSg: 'geoserver', verWms: '1.3.0'}, es: {impor: false}, inf: {desc: null, nom: null, palsCla: null}};
		switch (nomPlantilla) {
			case 'cg':
				resp = plantillaCg;
				break;
			case 'grcsgs':
				resp = plantillaGrcsgs;
				break;
			case 'sg':
				resp = plantillaSg;
				break;
		}
		$.each(atrs, function(ind, val) {
			resp = resp[val];
			if (!vd(obj[val])) {
				obj[val] = resp;
			}
			obj = obj[val];
		});
		if (vd(obj)) {
			resp = obj;
		}
		return resp;
	};

	var evosa = function evaluarValorDeObjetosDeAplicacion(obj, nomPlantilla, atrs) {
		var resp = [];
		$.each(atrs, function(ind) {
			resp.push(evoa(obj, nomPlantilla, atrs[ind]));
		});
		return resp;
	};

	var lvm = function lanzarVentanaModal(nom, plantilla, dims, elesCu, esAnim, funs) {
		var durAnim = apl.propsGens.durs[0];
		if (!esAnim) {
			durAnim = 1;
		}
		if (!vd(dims)) {
			dims = ['30%', '40%', '40%', '30%'];
		}
		ovm(esAnim,
			function () {
				var ele = $('#modal-window'), eleCa = $('#modal-window-header'), eleCu = $('#modal-window-body'), 
					eleC = $('#scroll-modal-window-body-content'), eleA = $('#mask-modal-window-body-area'),
					eleBCerrar = $('#modal-window-header-options-close-button'), eleBAceptar = $('#modal-window-body-options-accept-button'), eleBCancelar = $('#modal-window-body-options-cancel-button'),
					eleOps = $('#modal-window-body-options');
					
				if (nom === null) {
					eleCa.css({display: 'none'});
					eleCu.css({height: 'calc(100% - 10px)'});
				} else {
					$('#modal-window-header-text').html(nom);
					eleCa.css({display: 'block'});
					eleCu.css({height: 'calc(100% - 35px)'});
				}
				if (!vd(plantilla) || plantilla === 0) {
					eleC.css({height: '100%'});
					eleOps.css({display: 'none'});
				} else {
					eleC.css({height: '85%'});
					eleOps.css({display: 'block'});
				}
				switch (plantilla) {
					case 1:
						eleBAceptar.css({display: 'block', left: '25%'}).find('> p').html('Aceptar');
						eleBCancelar.css({display: 'block', right: '25%'}).find('> p').html('Cancelar');
						break;
				}
				eleA.html(elesCu);
				ele.css({display: 'block', height: dims[2], width: dims[1]}).animate(
					{
						left: dims[3],
						top: dims[0]
					},
					durAnim,
					function () {
						ge(['vm'], [funs[0], funs[1]]);
						lf(funs[2]);
					}
				);
			}
		);
	};

    var meApl = function modficarEstadoDeLaAplicacion(e, dats) {
        var eleBarraDeEDeApp = $('#status-application-bar');
		try {
			apl.e = e;
			eleBarraDeEDeApp.attr('value', e);
			switch (apl.e) {
				case 1:
					rp('Solicitud de servicios y capas', 'http://10.10.20.42:8080/SIUPRAWS/webresources/obj.servicios', {}, [main, e], [main, 'err']);
					break;
				case 2:
					rp_pru('Solicitud de árbol de consultas', 'http://localhost:8080/SIUPRAWS/webresources/obj.departamentos', {}, [main, e], [main, 'errF']);
					break;
				case 3:
					if (!vd(apl.cs.dats[apl.cs.sel])) {
						apl.cs.dats[apl.cs.sel] = {op: dats};
					}
					//if (!vd(apl.cs.dats[apl.cs.sel].op)) {
					if (!vd(dats)) {
						dats = {datsOp: apl.cs.mscs.mcsPpl.ops[apl.cs.sel]};
					}
					apl.cs.dats[apl.cs.sel].op = dats;
					//}
					if (!vd(apl.cs.dats[apl.cs.sel].fis)) {
						rp('Solicitud de posibles filtros de consulta por id', 'url', {idC: apl.cs.sel}, [main, e], [main, 'errF-c']);
					} else {
						main(3, apl.cs.dats[apl.cs.sel].fis);
					}
					break;
				case 4:
					rp('Solicitud de datos consulta por id', 'url', {idC: apl.cs.sel, fis: dats}, [main, e], [main, 'errF-c']);
					break;
				case 5:
					//Intentar mejorar esto
					if (!bPrimeraCarga) {
						$('#launch-application-information').animate(
							{
								opacity: 1
							}, 
							apl.propsGens.durs[0],
							function () {
								eleBarraDeEDeApp.animate(
									{
										opacity: 0
									},
									apl.propsGens.durs[0],
									function () {
										var eleB = $('#launch-application-button');
										eleBarraDeEDeApp.css('display', 'none');
										eleB.css('display', 'block').animate(
											{
												opacity: 1
											},
											apl.propsGens.durs[0] * 2,
											function () {
												eleB.mouseup(
													function () {
														ovm(true, 
															function () {
																//<OJO estos estilos se deben aplicar bien, verificar si es buena idea que el que llame la función lanzarVentanaModal seleccione los estilo de este>
																$('#modal-window').css({border: '1px solid black'});
																$('#modal-window-header').css({border: '1px solid black'});
																$('#modal-window-body').css({border: '1px solid black'});
																//</OJO>
																dp(true,
																	function () {
																		ade('#scroll-modal-window-body-content');
																	}
																);
															}
														);
													}
												);
											}
										)
									}
								);
							}
						);
					}
					aei();
					lf([main, e]);
					break;
			}
		} catch (err) {
			rl('err', 'Cargando datos a la aplicación', 'Durante la carga de algún dato se cometió una excepción y es posible que no se pueda continuar', {dats: arguments, men: err.message})
		}
    };

	var ovm = function ocultarVentanaModal(esAnim, fun) {
		var ele = $('#modal-window'), durAnim = apl.propsGens.durs[0];
		if (!esAnim) {
			durAnim = 1;
		}
		ele.animate(
			{
				top: '100%'
			},
			durAnim,
			function () {
				ele.css({display: 'none'});
				lf(fun);
			}
		);
	};

	var racsm = function removerArbolDeCapasGeograficasDeMapa(alM) {
		var objM = apl.ast.dats.atMaps[alM];
		objM.eleA.css('width', '100%');
		objM.acsgs.eleE.remove();
		objM.acsgs.e = false;
		rl('adv', concat('No se ha cargado ningún elemento en el árbol de capas del mapa >', alM, '<'), 'Ninguna capa geográfica fue representada en el árbol de capas, al parecer por problemas de conexión con los servicios');
	};

	var rcgac = function removerCapaGeograficaDeElementoDeArbolDeCapas(alM, alGrcsgs, alCg) {
		var objM = apl.ast.dats.atMaps[alM], objGrcsgs = objM.acsgs[alGrcsgs];
		objGrcsgs.nscsgs[alCg].eleA.remove();
		objGrcsgs.lon = objGrcsgs.lon - 1; 
		if (objGrcsgs.eleA.find('ul').children().length === 0) {
			objGrcsgs.eleA.remove();
			rl('adv', concat('Se ha eliminado el elemento >', alGrcsgs, '< del árbol de capas del mapa >', alM, '<'), 'Ninguna capa geográfica de este grupo pudo ser representada en el árbol de capas, al parecer por problemas de conexión con el servicio');
			if (objM.acsgs.eleC.children().length === 0) {
				racsm(alM);
			}
		}
	};

    var sat = function seleccionarAreaDeTrabajo(idAt) {
		var elePa = apl.ast.dats[idAt].eleB;
        if (apl.ast.e === 0 && apl.ast.sel !== idAt) {
            apl.ast.e = 2;
            dbas();
            apl.ast.eleC.animate({top: concat(-100 * (apl.ast.dats[idAt].or), '%')},
                apl.propsGens.durs[0],
                function () {
					ccc(elePa.find('div:nth-child(2)'), 'bc4', 'bc3');
                    ccc(elePa.find('div:nth-child(1)'), 'element-navigation-description', 'element-navigation-description-selected');
                    apl.ast.sel = idAt;
                    apl.ast.dats[idAt].sel = true;
                    apl.ast.e = 0;
                }
            );
        }
    };
    //De eventos e interacciones
	var ade = function configurarDeslizadorAElemento(idEle) {
		$(idEle).mCustomScrollbar(
			{
				advanced:{updateOnContentResize: true},
				autoHideScrollbar: true,
				keyboard:{enable: true},
				theme: 'dark-thin',
				scrollbarPosition: 'inside',
				scrollButtons:{enable: true},
				scrollInertia: 0
			}
		);
	};

	var afee = function agregarFuncionAEventoDeElemento(ele, tEve, fun, t) {
		ele.ready(
			function () {
				if ($(document.body).find($(ele)).length > 0 && (!vd(t) || t === 0)) {
					$(ele).on(tEve, fun);
				} else {
					if (tEve === 'mouseenter') {
						tEve = 'mouseover';
					} else if (tEve === 'mouseleave') {
						tEve = 'mouseout';
					}
					$.each(ele,
					   function (ind, val) {
							if (esF(val.addEventListener)) {
								val.addEventListener(tEve, fun, false);
							} else if (val.attachEvent) {
								val.attachEvent(concat('on', tEve), fun);
							} else {
								rl('adv', concat('Agregando función al evento del elemento >', ele.selector, '<'), 'No se ha sido posible agregar una función al evento de un elemento dado que no se ha podido establecer la manera de hacerlo');
							}
					   }
					);
				}
			}
		);
		return ele;
	};

    var ge = function generarEventos(ts, dats) {
		var val = null;

		//<Verificar que todos los target apliquen> 
		
		//Eventos de: acg: Atributos de capa geográfica, dih: data- interacción hover,  gcg: grupo de capas geográficas, m: mapa, mc: menú de consultas, n: navegación, ncg: nodo capa geográfica, omc: opción de menú de consultas, vm: ventana modal
		
		try {
			if(!vd(ts)) {
				ts = ['acg', 'dih', 'gcg', 'm', 'mc', 'n', 'ncg', 'omc', 'vm'];
			}
			if (ts.indexOf('acg') > -1) {
				$.each(dats.eleC.find('.generated-element-attribute-geographic-layer-toggle-button'),
					function(ind, val) {
						var ele = $(val);
						afee(ele, 'mouseup',
							function () {
								var atr = dats[ele.data('alatrcg')];
								if (atr.es.desp) {
									ele.attr('src', ele.attr('src').replace('hide', 'show'));
									atr.eleCu.hide();
								} else {
									ele.attr('src', ele.attr('src').replace('show', 'hide'));
									atr.eleCu.show();
								}
								atr.es.desp = !atr.es.desp;
							}
						);
					}
				);
			}
			if (ts.indexOf('dih') > -1) {
				$('[data-t="mas"]').remove();
				$('[data-i="ratonsobre"]').append(ce('div', {'data-t': 'mas'}, 'cup h100 pa w100', {}));
				val = $('[data-t="mas"]');
				afee(val, 'mouseenter', 
					function (arg) {
						var ele = $(arg.target), elePa = $(ele.parent()), atrFu = elePa.find('img').attr('src');
						elePa.find('img').attr('src', concat(atrFu.substring(0, atrFu.lastIndexOf('.')), '-hover.png'));
					}
				);
				afee($('[data-t="mas"]'), 'mouseleave', 
					function (arg) {
						var ele = $(arg.target), elePa = $(ele.parent());
						elePa.find('img').attr('src', elePa.find('img').attr('src').replace('-hover', ''));
					}
				);
			}
			if (ts.indexOf('gcg') > -1) {
				afee(dats.eleCa, 'mouseup',
					function () {
						if (dats.es.desp) {
							dats.eleCu.hide();
						} else {
							dats.eleCu.show();
						}
						dats.es.desp = !dats.es.desp;
					}
				);
			}
			if (ts.indexOf('m') > -1) {
				//m y mcs pueden ser uno solo que sea algo como generales 
				if (!apl.ast.dats.atMaps[dats].eves.cr) {
					apl.ast.dats.atMaps[dats].ol.getView().on('change:resolution',
						function() {
							//<Ojo con dats: verificar que se cumpla para todos>
							ecsgsvr(dats);
							//</Ojo con este dats>
						}
					);
					apl.ast.dats.atMaps[dats].eves.cr = true;
				}
			}
			if (ts.indexOf('mc') > -1) {
				//Estos métodos solo se pueden llamar cuando se hayán cargado todos los menús
				afee($('[data-i="ratonsobreEleBMcs"]'), 'mouseenter', 
					function (arg) {
						var ele = $(arg.target), elePa = $(ele.parent());
						//Ocultar todos los demás menús
						ccc(elePa, 'bc3', 'aplBc0');
						val = $(concat('[data-imcsee="', ele.data('imcseb'), '"]'));
						val.css({display: 'block'});
					}
				);
				afee($('[data-i="ratonsobreEleEMcs"]'), 'mouseleave', 
					function (arg) {
						var ele = $(arg.currentTarget), elePa = $(ele.parent());
						val = $(concat('[data-imcseb="', ele.data('imcsee'), '"]')).parent();
						
						ccc(val, 'aplBc0', 'bc3');
						ele.css({display: 'none'});
					}
				);
				//Cargar consultas rápidas también
			}
			if (ts.indexOf('n') > -1) {
				$('#wrapper-navigation-content ul li div:last-child').mouseenter(
					function (arg) {
						var ele = $(arg.target), elePa = $(ele.parent()), atrFu = elePa.find('img').attr('src');
						ele.css({width: '450%'});
						$(elePa[0].firstElementChild).css({left: '100%'});
						elePa.find('img').attr('src', concat(atrFu.substring(0, atrFu.lastIndexOf('.')), '-hover.png'));
					}
				).mouseleave(
					function (arg) {
						var ele = $(arg.target), elePa = $(ele.parent());
						ele.css({width: '100%'});
						$(elePa[0].firstElementChild).css({left: '-350%'});
						elePa.find('img').attr('src', elePa.find('img').attr('src').replace('-hover', ''));
					}
				).mouseup(
					function (arg) {
						sat($(arg.target).data('nat'));
					}
				);
			} 
			if (ts.indexOf('ncg') > -1) {
				afee(dats.eleCa.find('.generated-element-node-geographic-layer-header-text'), 'mouseup',
					function () {
						if (dats.es.desp) {
							dats.eleCu.hide();
						} else {
							dats.eleCu.show();
						}
						dats.es.desp = !dats.es.desp;
					}
				);
				afee(dats.eleCa.find('.generated-element-node-geographic-layer-header-button'), 'mouseup',
					function (arg) {
						var ele = $(arg.target);
						val = apl.dats.csgs.dats[dats.atrs.alCg];
						if (val.es.act) {
							if (val.es.vi) {
								ele.attr('src', ele.attr('src').replace('hide', 'show'));
							} else {
								ele.attr('src', ele.attr('src').replace('show', 'hide'));
							}
							val.ol.setVisible(val.es.vi = !val.es.vi);
						}
					}
				);
				$.each(dats.eleCu.find('.generated-element-attribute-geographic-layer-range'),
					function(ind, val) {
						//Meétodo general para setar lo valores tanto en el mapa como en la variable de cg.conf
						var ele = $(val);
						afee(ele, 'change',
							function (arg) {
								var tipoConfiguracion = $(arg.target).data('pccg');
								switch (tipoConfiguracion) {
									case 'bri':
										apl.dats.csgs.dats[dats.atrs.alCg].ol.setBrightness(ele.val());
										apl.dats.csgs.dats[dats.atrs.alCg].conf.bri = ele.val();
										break;
									case 'contr':
										apl.dats.csgs.dats[dats.atrs.alCg].ol.setContrast(ele.val());
										apl.dats.csgs.dats[dats.atrs.alCg].conf.contr = ele.val();
										break;
									case 'opa':
										apl.dats.csgs.dats[dats.atrs.alCg].ol.setOpacity(ele.val());
										apl.dats.csgs.dats[dats.atrs.alCg].conf.opa = ele.val();
										break;
								}
							}
						);
					}
				);
			}
			if (ts.indexOf('omc') > -1) {
				//Analizar si aplica para todos los menús de consutla o ajustarlo para tal comportamiento
				dats.datsOp.eleC.append(dats.ele);
				afee(dats.ele, 'mouseenter',
					function () {
						val = apl.cs.mscs.mcsPpl.nivs[dats.indNiv - 1].ops;
						$.each(val,
							function (id) {
								val[id].eleC.find('.generated-element-option-query-menu-text-selected').removeClass('generated-element-option-query-menu-text-selected');
							}
						)
						dats.ele.addClass('generated-element-option-query-menu-text-selected');
						cnmcp(dats.indNiv, dats.idMc, dats.datsOp.nom, dats.ele);
					}
				);
				if (vd(dats.datsOp.es) &&  dats.datsOp.es.c) {
					dats.ele.attr({'data-ic': dats.idMc});
					afee(dats.ele, 'mouseup',
						function () {
							apl.cs.sel = dats.idMc;
							meApl(3, dats);
						}
					);
				}
			}
			if (ts.indexOf('vm') > -1) {
				var eleBCerrar = $('#modal-window-header-options-close-button'), eleBAceptar = $('#modal-window-body-options-accept-button'), eleBCancelar = $('#modal-window-body-options-cancel-button');
				eleBAceptar.unbind();
				afee(eleBAceptar, 'mouseup', 
					function () {
						lf(dats[0]);
					}
				);
				eleBCerrar.unbind();
				afee(eleBCerrar, 'mouseup', 
					function () {
						ovm(true, dats[1]);
					}
				);
				eleBCancelar.unbind();
				afee(eleBCancelar, 'mouseup', 
					function () {
						ovm(true, dats[1]);
					}
				);
			}
		} catch (err) {
			rl('err', concat('Generando eventos de >', String(ts), '<'), 'Se produjo una excepción al generar eventos de controles', {dats: {d0: ts, d1: dats}, men: err.message});
		}
    };

	
	//, .scroll-query-menu-options-content , 
	
	$(window).load(function(){
		ade('#scroll-layer-tree-geographic-map-main-list');
	});

    $( window ).resize(function() {
        aei();
    });

    //Función principal
    function main(paso, dats, alM) {
        if (!vd(alM)) {
			alM = 'mPpl';
		}
		if (esN(paso)) {
            switch (paso) {
                case 0:
					apl.cs.sel = dats.idC;
					objFisSels = dats.objFi;
                    apl.ast.eleC = $('#mask-main-content');
					apl.ast.e = 0;
					cm(alM);
					ge(['dih']);
                    meApl(1);
                    break;
                case 1:
					/*<DATOS FICTISIOS, Ojo con los órdenes>*/
					dats = {
						acsgs: {
							grcsgsCt:{
								inf:{ 
									desc: 'Cattografía temática del país', 
									nom:'Cartografía temática', 
									palsCla:'cartografía, temática'
								}
							},
							grcsgsZe:{inf:{desc:'Zonas de exclusión legal 2010, 2014', nom:'Zonas de exclusión', palsCla:'zonas, exclusión, 2010, 2014'}},
							grcsgsWu:{inf:{desc:'WMS UPRA', nom:'WMS UPRA', palsCla:'WMS, UPRA'}}
						},
						ssgs: {
							sgCt: {acc:0, conf: {tServSg:'Geoserver', url:'http://10.10.30.8:8082/geoserver/carto_tematica/wms?'}, es:{impor: false}, inf:{desc:'Primer servicio de prueba', nom:'UPRA cartografía temática', palsCla:'UPRA, cartografía, temática'}},
							sgTest: {conf: {tServSg:'Geoserver', url:'http://10.10.30.8:8082/geoserver/test/wms?'}, es:{impor: false}, inf:{desc:'Segundo servicio de prueba', nom:'UPRA test', palsCla:'UPRA, test, zonificacón'}},
							sgWu: {conf: {url:'http://10.10.30.8:8082/geoserver/wms_upra/wms?', verWms: '1.1.0'}},
							
							
							
							sgFondo: {conf: {url:'http://demo.boundlessgeo.com/geoserver/wms?'}}
						},
						csgs: {
							cgFondo: {acc: 0, alGrcsgs: 'grcsgsZe', alSg: 'sgFondo', conf: {reMa: 0.010986328125, fis: null, nomCg: 'ne:NE1_HR_LC_SR_W_DR', tCg: 'WMS.TILE'}, es: {autoIden: false, transp: true, vi: true}, inf: {nom: 'Fondo'}}
							,cg1: {acc: 0, alGrcsgs: 'grcsgsCt', alSg: 'sgCt', conf: {reMa: 6.866455078125E-4, reMi: 0.0054931640625, fis: null, lims: '-85.023, -7.228, -66.847, 15.167', nomCg: 'icare', opa: 1, tCg: 'WMS.TILE'}, es: {autoIden: false, viVisGen: true, caLe: true, iden: true, or: true, transp: true, vi: true}, inf: {anio: 2014, desc: 'Índice municipal de formalidad estimado a partir de ICARE', esc: '1:100.000', fu: 'UPRA', img: 'images/layersGeographic/ic.png', nom: 'ICARE', palsCla: 'Índice, formalidad, ICARE'}}
							,cg2: {acc: 0, alGrcsgs: 'grcsgsCt', alSg: 'sgCt', conf: {reMa: 6.866455078125E-4, reMi: 0.0054931640625, fis: null, lims: '-85.001, -7.202, -66.803, 15.104', nomCg: 'produccion_nal_mpio', opa: 0.5, tCg: 'WMS.TILE'}, inf: {anio: 2014, desc: 'Producción nacional por municipio de Colombia', esc: '1:100.000', fu: 'UPRA', img: 'images/layersGeographic/pnm.png', nom: 'Producción nacional por municipio', palsCla: 'producción, nacional, municipial'}}
							//,cg3: {alGrcsgs:, alSg: 'sgCt', conf: {fis: null, nomCg: 'prod_mpio_arroz', opa: 1, sire: 'EPGS:4326'}, es: {autoIden: false, viVisGen: false, caLe: true}, inf: {anio: 2014, desc: 'Producción nacional por municipio de arroz en Colombia', esc: '1:100.000', fu: 'MADR', nom: 'Producción nacional por municipio - Arroz', palsCla: 'arroz, producción, nacional'}}
							//,cg4: {acc: 0, alGrcsgs:, alSg: 'sgTest', conf: {reMa: 6.866455078125E-4, reMi: 0.0054931640625, fis: null, nomCg: 'zonasexclusionlegal2010', opa: 1, tCg: 'WMS.TILE'}, es: {autoIden: false, transp: true, vi: false}}
							,cg5: {alGrcsgs: 'grcsgsWu', alSg: 'sgTest', conf: {reMa: 6.866455078125E-4, fis: null, nomCg: 'zonasexclusionlegal2014', opa: 1, sire: 'EPGS:4326', tCg: 'WMS.TILE'}, inf: {anio: 2014, fu: 'UPRA', img: 'images/layersGeographic/default.png', nom: 'Zonas de exclusión legal 2014'}}
							//,cg6: {acc: 0, alGrcsgs:, alSg: 'sgWu', conf: {fis: null, nomCg: 'ADT_pot_riego_drenaje', opa: 1, sire: 'EPGS:4326'}}
							//,cg7: {alGrcsgs:, alSg: 'sgWu', conf: {fis: null, lims: '-85.023, -7.228, -66.847, 15.167', nomCg: 'MDT_exclusiones_mercado_tierras', opa: 0.1, tCg: 'WMS.TILE'}, es: {autoIden: false, viVisGen: true, caLe: true, iden: true, or: true, transp: true, vi: false}}
							//,cg8: {alGrcsgs:, alSg: 'sgWu', conf: {nomCg: 'VDS_vocacion_ganadera'}, inf: {nom: 'VDS_vocacion_ganadera'}}
							//,cg9: {acc: 0, alGrcsgs:, alSg: 'sgWu', conf: {fis: null, nomCg: 'VDS_vocacion_agricola', opa: 0.7, sire: 'EPGS:4326'}, es: {autoIden: false, transp: true, vi: false}}
						}
					};
					/*</DATOS FICTISIOS>*/

					//Que los trimestres y semestres lleguen como array [0, 2] indicando que va desde el mes 0 al mes dos por ejemplo y en este punto convertirlos al texto es decir "Enero a Marzo"
					
					
					apl.dats.ssgs.dats = dats.ssgs;
					agacg(alM, dats.acsgs);
					acgm(alM, dats.csgs);

					//Cargar overview
					meApl(2);
                    break;
				case 2:
					/*<DATOS FICTISIOS, Ojo con los órdenes>
					dats = {
						ens: {
							deps: {
								dats: {
									'05': {
										dats: {
											ens: {
												muns: ['05001', '05002', '05003']
											}
										},
										ext: [],
										nomCorto: 'ANT',
										nom: 'Antioquia',
										nomLargo: 'Antioquia'
									},
									'17': {
										dats: {
											ens: {
												muns: ['17001']
											}
										},
										ext: [],
										nomCorto: 'CAL',
										nom: 'Caldas',
										nomLargo: 'Caldas',
									}
								},
								nom: 'Departamentos'
							},
							muns: {
								dats: {
									'05001': {
										ext: [],
										nomCorto: 'MED',
										nom: 'Medellín',
										nomLargo: 'Medellín'
									},
									'05002': {
										ext: [],
										nom: 'Guatapé',
										nomLargo: 'Guatapé'
									},
									'05003': {
										ext: [],
										nom: 'Toribio',
										nomLargo: 'Toribio'
									},
									'17001': {
										ext: [],
										nomCorto: 'MAN',
										nom: 'Manizales',
										nomLargo: 'Manizales'
									}
								},
								nom: 'Municipios',
							},
							regis: {
								dats: {
									moj: {
										nom: 'La Mojana, sur de Bolivar',
										dats: {
											ens: {
												muns: ['05001', '05002']
											}
										}
									}
								},
								nom: 'Regionales',
							},
							terrs: {
								dats: {
									terr1: {
										nom: 'Territorial 1'
									}
								},
								nom: 'Territoriales',
							}
						},
						mcsAr: {
							dats: ['ti', 'so', 'subGa12']
						},
						mcsPpl: {
							dats: {
								ag: {
									alOpPa: null,
									tex: 'Solo agua'
								},
								ai: {
									alOpPa: null,
									nom: 'Aires del ambiente',
									tex: 'Información sobre el aire'
								},
								pu: {
									alOpPa: 'ai',
									tex: 'Puro',
									es: {c: true}
								},
								ntp: {
									alOpPa: 'ai',
									tex: 'No tan puro',
									es: {c: true}
								},
								ti: {
									alOpPa: null,
									es: {c: true},
									tex: 'Información nacional, departamenal, municipal y mas de la "Tierra" en Colombia'
								},
								ga: {
									alOpPa: 'ag',
									nom: 'Subgaseosos',
									tex: 'Gaseoso'
								},
								subGa1: {
									alOpPa: 'ga',
									nom: 'Sub sub gaseoso 1',
									tex: 'sub Gaseoso 1'
								},
								subGa11: {
									alOpPa: 'subGa1',
									es: {c: true},
									tex: 'sub Gaseoso 1 1'
								},
								subGa12: {
									alOpPa: 'subGa1',
									es: {c: true},
									tex: 'sub Gaseoso 1 2'
								},
								subGa13: {
									alOpPa: 'subGa1',
									es: {c: true},
									tex: 'sub Gaseoso 1 3'
								},
								subGa14: {
									alOpPa: 'subGa1',
									es: {c: true},
									tex: 'sub Gaseoso 1 4'
								},
								subGa15: {
									alOpPa: 'subGa1',
									es: {c: true},
									tex: 'sub Gaseoso 1 5'
								},
								subGa2: {
									alOpPa: 'ga',
									nom: 'Estados sub subgaseosos 2',
									tex: 'sub Gaseoso 2'
								},
								subGa21: {
									alOpPa: 'subGa2',
									es: {c: true},
									tex: 'sub Gaseoso 2 1'
								},
								subGa22: {
									alOpPa: 'subGa2',
									es: {c: true},
									tex: 'sub Gaseoso 2 2'
								},
								subGa23: {
									alOpPa: 'subGa2',
									es: {c: true},
									tex: 'sub Gaseoso 2 3'
								},
								subGa3: {
									alOpPa: 'ga',
									es: {c: true},
									tex: 'Nombre muy largo de sub Gaseoso 1 para ver como se comporta con textos largos o muy largos'
								},
								subGa4: {
									alOpPa: 'ga',
									es: {c: true},
									tex: 'Sub Gaseoso 4 es el último sub de gaseoso'
								},
								li: {
									alOpPa: 'ag',
									es: {c: true},
									tex: 'Líquido'
								},
								so: {
									alOpPa: 'ag',
									es: {c: true},
									tex: 'Sólido'
								}
							}
						}
					};
					</DATOS FICTISIOS>*/

					//Revisar que todas estas variables sean requeridas y que no se repitan entre ellas
					var objDesEn = null, objMcs = apl.cs.mscs.mcsPpl, objMc = null, objNiv = null, objOp = null, objTemp = null, arrMcs = [], arrNivs = objMcs.nivs, 
						indNiv = null, alOp = null, eleE = null;
					try {
						apl.dats.ens.dats = dats.ens;
						for (id in dats.mcsPpl.dats) {
							objMc = dats.mcsPpl.dats[id];
							alOp = objMc.alOpPa;
							indNiv = null;
							if (!vd(alOp)) {
								indNiv = 0;
							} else {
								for (ind = 0; ind < arrMcs.length; ind = ind + 1) {
									if (arrMcs[ind].indexOf(alOp) > -1) {
										indNiv = ind + 1;
										break;
									}
								}
							}
							if (indNiv !== null) {
								if (!vd(arrMcs[indNiv])) {
									arrMcs[indNiv] = '';
								}
								arrMcs[indNiv] = concat(arrMcs[indNiv], ' ', id);
								if (arrNivs.length <= indNiv) {
									eleE = $(objMcs.eleE.find('> li')[indNiv]);
									arrNivs.push({
										eleC: eleE.find('.scroll-query-menu-options-content'),
										eleE: eleE
									});
									objNiv = arrNivs[indNiv];
									switch (indNiv) {
										case 0:
											objNiv.nom = 'Nivel 2';
											break;
										case 1:
											objNiv.nom = 'Nivel 3';
											break;
										case 2:
											objNiv.nom = 'Nivel 4';
											break;
									}
									objNiv.ops = {};
								} else {
									objNiv = arrNivs[indNiv];
								}
								objOp = objNiv.ops[id] = objMc;
								objOp.eleC = ce('li', {}, 'dib', {});
								if (!vd(objOp.nom) && vd(objNiv.nom)) {
									objOp.nom = objNiv.nom;
								}
								ge(['omc'],
									{
										ele: ce('p', {'data-lh': '2'}, 'cup generated-element-option-query-menu-text', {'line-height': '11px'}).html(objMc.tex),
										datsOp: objOp,
										idMc: id,
										indNiv: indNiv + 1
									}
								);
								apl.cs.mscs.mcsPpl.ops[id] = objOp;
							} else {
								//Generar casos en lo que esto pueda ocurrir y generar también acción a tomar
								console.log('Ids no encontrados al generar el menú de consultas: ', id);
							}
						}
						cnmcp(0);
						//Cargar acá el menú de acceso rápido
						arrMcs = dats.mcsAr.dats;
						//Scrolls a uls de opciones
						ge(['mc']);
					} catch(err) {
						rl('err', 'Generando el menú de consultas', 'Mientras se generaba menu de consultas ocurrió una excepción', err.message);
						//Elminar los dos botones de temas asociados "Temas 1" y "Temas 2"
					}
					meApl(3, null);
					break;
				case 3:
					/*<DATOS FICTISIOS>*/
					//Llegan los posibles filtros de la consulta
					dats = {
						ens: {
							dats: {
								nal: {
									dats: true,
								},
								deps: {
									dats: ['05', '17']
								},
								muns: {
									dats: []
								},
								regis: {
									dats: ['moj']
								},
								terrs: {
									dats: []
								}
							}
						},
						ots: {
							dats: {
								ot2: {
									dats: ['Consolidado', 'Participación', 'Sumatoria'],
									nomFi: 'Tipo de consulta:',
									subTFi: 'radio',
									tFi: 'input',
									tFiPa: null
								},
								ot1: {
									dats: ['Compras', 'Ventas', 'Otros'],
									nomFi: 'Transcacciones y actos registrados:',
									subTFi: 'checkbox',
									tFi: 'input',
									tFiPa: null
								}
							}
						},
						pers: {
							dats: {
								anios: {
									dats: [2015, 2012, 2011, 2013]
								},
								meses: {
									dats: []
								},
								trimestres: {
									dats: []
								},
								semestres: {
									dats: []
								}
							}
						}
					};
					/*</DATOS FICTISIOS>*/
					var objC = apl.cs.dats[apl.cs.sel], objFichaI = null, objFichaJ = null, objFichaK = null, objFichaL = null, objFichaM = null, valFicha = null, objAtrsEle = null, objDats = null, tNod = null, arrEles = null;
					if (!vd(objC.fis)) {
						objC.fis = {
							dats: {},
							eleCu: ce('div', {}, 'w100', {})
						};
						for (var idI in dats) {
							//Ojoooooooooo
							objFichaI = objC.fis.dats[idI] = {
								cont: 0,
								dats: {},
								eleC: ce('ul', {}, 'w100', {})
							};
							objFichaJ = apl.cs.datsFisGens.dats[idI];
							objFichaK = dats[idI].dats;
							for (var idJ in objFichaK) {
								//Ajustar para que según el tipo de elemento HTML se ajusten las consultas > Tema 1
								if (vd(objFichaJ.dats)) {
									objFichaL = objFichaJ.dats;
								} else {
									objFichaL = {};
									objFichaL[idJ] = objFichaK[idJ];
								}
								if (vd(objFichaK[idJ].nomFi)) {
									valFicha = objFichaK[idJ].nomFi;
								} else {
									valFicha = objFichaL[idJ].nomFi;
								}
								objFichaI.dats[idJ] = {
									eleA: ce('li', {}, '', {}),
									eleTex: ce('label', {'for': concat('generated-element-input-', idI ,'-', idJ)}, 'dib filter-query-option-label m0', {}).html(valFicha)
								};
								objFichaI.dats[idJ].eleTex.data(concat(idI, idJ), idJ);
								if (objFichaL[idJ].tFiPa !== null) {
									objAtrsEle = {id: concat(objFichaJ.tFi, '-', idI ,'-', idJ)};
									if (vd(objFichaJ.subTFi)) {
										objAtrsEle.type = objFichaJ.subTFi;
										objAtrsEle.name = idI;
										objAtrsEle.value = idJ;
									}
									objFichaI.dats[idJ].ele = ce(objFichaJ.tFi, objAtrsEle, 'dib filter-query-option-input m0', {});
								}
								arrEles = [objFichaI.dats[idJ].ele, objFichaI.dats[idJ].eleTex];
								if (objFichaK[idJ] === true) {
									objFichaI.dats[idJ].dats = objFichaK[idJ];
									objFichaI.cont = objFichaI.cont + 1;
								} else {
									objDats = objFichaI.dats[idJ];
									tNod = objFichaL[idJ].tFi;
									if (!vd(objDats.eleFi) && tNod === 'select') {
										//Ajustar para que según el tipo de elemento HTML se ajusten las consultas > Tema 1
										objDats.eleFi = ce(tNod, {}, '', {});
									}
									arrEles.push(ce('br', {}, '', {}), objDats.eleFi);
									if (tNod === 'select') {
										tNod = 'option';
									}
									if (objFichaK[idJ].dats.length === 0) {
										if (idI === 'ens') {
											objDats = objFichaI.dats[idJ].dats = apl.dats[idI].dats[idJ].dats;
										} else if (idI === 'pers') {
											objDats = objFichaI.dats[idJ].dats = objFichaL[idJ].dats;
										}
									} else {
										objDats = objFichaI.dats[idJ].dats = {};
										if (idI === 'ens') {
											for (ind = 0; ind < objFichaK[idJ].dats.length; ind = ind + 1) {
												valFicha = objFichaK[idJ].dats[ind];
												objDats[valFicha]  = objFichaI.dats[idJ].dats[valFicha] = apl.dats[idI].dats[idJ].dats[valFicha];
											}
										} else {
											objDats = objFichaI.dats[idJ].dats = objFichaK[idJ].dats;
										}
									
									}
									//Almacenar los datos de objDats.elesFi en un objeto temporal para elminar if (!vd(objDats[idK].eleFi)) { , mirar si se puede reutilziar un objFicha
									objDats.elesFi = {};
									for (var idK in objDats) {
										if (idK !== 'elesFi')  {
											//<Ojooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo dependencias entre entidades y periodos>
											//Si un elemento por ejemplo periodos solo viene para un año entonces quitar el select de años y quitar el option de meses si solo viene por meses
											if (vd(objFichaL[idJ].pas)) {
												for (var idL in objFichaL[idJ].pas) {
													for (ind = 0; ind < objFichaL[idJ].pas[idL].length; ind = ind + 1) {
														//cargar lo elementos en el objeto objFichaM y onchange
													}
												}
											}
											if (!vd(objDats[idK].eleFi)) {
												objAtrsEle = {id: concat(objFichaL[idJ].tFi, '-', idJ ,'-', idK), value: idK};
												if (vd(objFichaL[idJ].subTFi)) {
													objAtrsEle.type = objFichaL[idJ].subTFi;
													objAtrsEle.name = idJ;
												}
												objDats.elesFi[idK] = {
													ele: ce(tNod, objAtrsEle, '', {})
												}
												//eleFicha para el texto en el caso de "ens" es igual a "ele" en el resto de casos va en "eleTex"
												if (idI === 'ens') {
													objDats.elesFi[idK].ele.html(objDats[idK].nom);
												} else {
													objDats.elesFi[idK].eleTex = ce('label', {'for': concat('generated-element-', objFichaL[idJ].tFi, '-', idJ ,'-', idK)}, '', {}).html(objDats[idK]);
												}
											}
											//</Ojo>
											if (vd(objFichaI.dats[idJ].eleFi)) {
												objFichaI.dats[idJ].eleFi.append(objDats.elesFi[idK].ele);
											} else {
												arrEles.push(objDats.elesFi[idK].ele, objDats.elesFi[idK].eleTex);
											}
										}
									}
									objFichaI.cont = objFichaI.cont + Object.keys(objFichaI.dats[idJ].dats).length;
								}
								objFichaI.dats[idJ].eleA.append(arrEles);
								objFichaI.eleC.append(objFichaI.dats[idJ].eleA);
							}
							objC.fis.eleCu.append(
								ce('p', {}, 'filter-query-title m0 w100', {}).html(objFichaJ.nomFi),
								objFichaI.eleC
							);
						}
					}
					if (!vd(objFisSels) &&
						(objC.fis.dats.ens.cont <= 1 && 
					    objC.fis.dats.pers.cont <= 1 &&
						objC.fis.dats.otrs.cont <= 1)) {
						objFisSels = objC.fis;
					}
					if (!vd(objFisSels)) {
						objFisSels = {};
						lvm('Selección de filtros de consulta', 1, null, objC.fis.eleCu, true,
							[
								function () {
									for(var idI in objC.fis.dats) {
										for (var idJ in objC.fis.dats[idI].dats) {
											objFichaI = objC.fis.dats[idI].dats[idJ];
											
											if(!vd(objFichaI.ele) || objFichaI.ele[0].checked ) {
												valFicha = objFichaI.eleTex.data(concat(idI, idJ));
												if(!vd(objFisSels[idI])) {
													objFisSels[idI] = {};
												}
												if(!vd(objFisSels[idI][valFicha])) {
													objFisSels[idI][valFicha] = [];
												}
												objFichaJ = objFisSels[idI][valFicha];
												//Establecer en la validación si el tipo es select, pensar en otros tipos de dropdowns que puedan aplicar o de tipo de filtros
												//Validación de datos mínimos y de valores selccionados padres e hijos, es decir si está seleccionado un input padre que seleccione opciones hijas
												//Que pasa cuando es solo true como nal
												if (!vd(objFichaI.eleFi)) {
													for(var idK in objFichaI.dats.elesFi) {
														if(objFichaI.dats.elesFi[idK].ele[0].checked) {
															objFichaJ.push(objFichaI.dats.elesFi[idK].ele.val());
														}
													}
												} else {
													objFichaJ.push(objFichaI.eleFi.val());
												}
											}
										}
									}
									//Si todo va bien>>>
									ovm(true, 
										function () {
											//Ajax load para los casos que aplique en bp
											bp(1, true, 
												function () {
													objFisSels.cSel = apl.cs.sel;
													meApl(4, objFisSels);
												}
											);
										}
									);
									//<<<
								},
								function () {
									meApl(5);
								}
							]
						);
					} else {
						objFisSels.cSel = apl.cs.sel;
						meApl(4, objFisSels);
					}
					break;
				case 4:
					var datsAst = [];
					/*<DATOS FICTISIOS>*/

					//Evaluar las consultas que se han cargado
					//Cargar datos de consulta
					//Al cargar las capas de consulta configurar el tipo de la capa como tipo 2 que indica que es una capa de consulta
					//Cargar el mapa y poner las capas entratntes en primer órden


					dats = {
						ast: {
							atMaps: {
								idAt: 'atMaps',
								dats: {
									acsgs: {
										csNuevas: {
											inf: { 
												desc: 'Datos de consultas', 
												nom:'Datos de consulta', 
												palsCla:'datos, consulta'
											}
										}
									},
									ssgs: {
										sgTest: {conf: {tServSg:'Geoserver', url:'http://10.10.30.8:8082/geoserver/test/wms?'}, es:{impor: false}, inf:{desc:'Segundo servicio de prueba', nom:'UPRA test', palsCla:'UPRA, test, zonificacón'}}
									},
									csgs: {
										cg5: {alGrcsgs: 'csNuevas', alSg: 'sgTest', conf: {reMa: 6.866455078125E-4, fis: null, nomCg: 'zonasexclusionlegal2010', opa: 1, sire: 'EPGS:4326', tCg: 'WMS.TILE'}, inf: {anio: 2014, fu: 'UPRA', img: 'images/layersGeographic/default.png', nom: 'Zonas de exclusión legal 2014'}}
									}
								},
								tex: 'Super mapa'
							},
							atGras: {
								plantilla: 1,
								dats: [
									{
										chart: {
											type: 'pie'
										},
										title: {
											text: ''
										},
										tooltip: {
											pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
										},
										plotOptions: {
											pie: {
												allowPointSelect: true,
												cursor: 'pointer',
												depth: 35,
												dataLabels: {
													enabled: true,
													format: '{point.name}'
												}
											}
										},
										series: [{
											type: 'pie',
											name: 'Porcentaje',
											data: [
												['Condicionante',     54.0],
												['Exclusión',    3.0],
												['Sin restricción',     43.0]
											]
										}]
									}

								]
							},
							atInf: {
								//titulo principal, subtitulos, textos, galeria de imágenes
							},
							atTabs: {
								plantilla: 1,
								dats: [
									{
										show: {
											//Agregar título
											toolbar: true,
											footer: true
										},
										columns: [
											{field: 'recid',  caption: '#',            size: '90px', sortable: true, attr: 'align=center'},
											{field: 'campo1', caption: 'Departamento', size: '40%',  sortable: true, resizable: true},
											{field: 'campo2', caption: 'Valor 1',      size: '20%',  sortable: true, resizable: true},
											{field: 'campo3', caption: 'Valor 2',      size: '20%',  sortable: true, resizable: true},
											{field: 'campo4', caption: 'Valor 3',      size: '20%',  sortable: true, resizable: true}
										],
										searches: [
											{field: 'campo1', caption: 'Departamento', type: 'text'},
											{field: 'campo2', caption: 'Valor 1',      type: 'numeric'},
											{field: 'campo3', caption: 'Valor 2',      type: 'numeric'}
										],
										sortData: [{field: 'recid', direction: 'ASC'}],
										records: [
											{recid: 1, campo1: 'CAQUETÁ',   campo2: 34,   campo3: 37.9, campo4: 25},
											{recid: 2, campo1: 'GUAVIARE',  campo2: 29.4, campo3: 34.4, campo4: 25},
											{recid: 3, campo1: 'AMAZONAS',  campo2: 27.2, campo3: 15.7, campo4: 25},
											{recid: 4, campo1: 'ANTIOQUIA', campo2: 9.4,  campo3: 12,   campo4: 25}
										]
									}
								]
							}
						},
						atSel: 'atMaps'
					};
					/*</DATOS FICTISIOS>*/
					
					//Cambiar datsOp por otro nombre
					var at = null, orAt = 0, eleFicha = null, indFicha = 0, texFicha = '', objFicha = apl.cs.dats[apl.cs.sel].op.datsOp;
					try {
						$('#query-title-text').html(objFicha.tex);
						while (vd(objFicha.alOpPa)) {
							texFicha = concat(apl.cs.mscs.mcsPpl.ops[objFicha.alOpPa].tex, '&nbsp;>&nbsp;', texFicha);
							objFicha = apl.cs.mscs.mcsPpl.ops[objFicha.alOpPa];
						}
						$('#wrapper-breadcrumb-content').html(texFicha);
						
						//Aca, ojo con la repeticion d id de tabla almacenar todos los datos de la consulta por filtro
						
						apl.ast.e = 1;
						dbas();
						apl.ast.sel = null;
						for (id in apl.ast.dats) {
							at = apl.ast.dats[id];
							at.eleA.remove();
							at.eleB.remove(); 
							at.or = null;
							at.sel = false;
							at.e = false;
						}
						for (var idI in dats.ast) {
							at = apl.ast.dats[idI];
							if (!vd(dats.atSel)) {
								dats.atSel = at.id;
							}
							at.eleB.find('p').html(vd(dats.ast[idI].tex) ? dats.ast[idI].tex : at.tex);
							at.or = orAt;
							apl.ast.eleC.append(at.eleA);
							$('#wrapper-navigation-content > ul:first-child').append(at.eleB);
							at.e = true;
							orAt = orAt + 1;
							if (esA(dats.ast[idI].dats)) {
								for (ind = 0; ind < dats.ast[idI].dats.length; ind = ind + 1) {
									switch (idI) {
										case 'atDocs':
											
											break;
										case 'atGras':
											indFicha = ind + 1;
											eleFicha = apl.ast.dats[idI].eleA.find(concat('#gra-', indFicha)).removeClass(concat('gra-', indFicha, '-template-1 tab-', indFicha, '-template-2 gra-', indFicha, '-template-3'));
											eleFicha.addClass(concat('tab-', indFicha, '-template-', dats.ast[idI].plantilla));
											dats.ast[idI].dats[ind].name = concat('grafico', indFicha);
											eleFicha.highcharts(
												dats.ast[idI].dats[ind]
											);
											break;
										case 'atTabs':
											indFicha = ind + 1;
											//Función para remover los templates de las grillas sin quemar su id, crear un variable # de templates en el objeto de ast
											eleFicha = apl.ast.dats[idI].eleA.find(concat('#tab-', indFicha)).removeClass(concat('tab-', indFicha, '-template-1 tab-', indFicha, '-template-2 tab-', indFicha, '-template-3'));
											eleFicha.addClass(concat('tab-', indFicha, '-template-', dats.ast[idI].plantilla));
											dats.ast[idI].dats[ind].name = concat('tabla', indFicha);
											eleFicha.w2grid(
												dats.ast[idI].dats[ind]
											);
											break;
									}
								}
							} else {
								if (idI === 'atMaps') {
									if (vd(dats.ast[idI].dats.ssgs)) {
										for (var idJ in dats.ast[idI].dats.ssgs) {
											if(!vd(apl.dats.ssgs.dats[idJ])) {
												apl.dats.ssgs.dats[idJ] = dats.ast[idI].dats.ssgs[idJ];
											} 
										}
									}
									
									console.log(dats.ast[idI].dats.acsgs);
									
									if (vd(dats.ast[idI].dats.acsgs)) {
										agacg('mPpl', dats.ast[idI].dats.acsgs);
									}
									if (vd(dats.ast[idI].dats.acsgs)) {
										acgm('mPpl', dats.ast[idI].dats.acsgs);
									}
								}
							}
						}


						ge(['n']);
						apl.ast.e = 0;
						sat(dats.atSel);
					} catch (err) {
						rl('err', 'Configurando los datos de una consulta', 'Sucedió un error mientras se intentaba configurar una consulta', {dats: dats, men: err.message, err: err});
					}
					meApl(5);
					break;
				case 5:
					//Acabar la carga cuando se carguen las capas ??? 
					if (bPrimeraCarga) {
						dp(true);
					}
					bPrimeraCarga = true;

					//<Borrar>
					console.log(apl.cs.sel, objFisSels, apl.logs);
					//</Borrar>

					objFisSels = null;
					break;
            }
        } else {
            rl(paso, 'Falló del sistema', 'El sistema cometió un error grave y es posible que no se pueda continuar con la ejecución del programa');
        }
    }; main(0, {idC: 'ti', objFi: {ens: {nal: true}}});
});