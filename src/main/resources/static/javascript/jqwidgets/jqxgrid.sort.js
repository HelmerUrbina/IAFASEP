/*
jQWidgets v12.2.0 (2021-Sep)
Copyright (c) 2011-2021 jQWidgets.
License: https://jqwidgets.com/license/
*/
/* eslint-disable */

(function(a){a.jqx.dataview.sort=function(){this.sortby=function(d,g,u){var o=Object.prototype.toString;if(g==null){this.sortdata=null;if(this.grid.sortmode!=="many"){this.sortcache={}}else{if(this.sortcache[d]){delete this.sortcache[d]}}this.grid._pagescache=new Array();this.grid._cellscache=new Array();if(this.grid.sortmode!=="many"){this.refresh();return}}if(this.grid.sortmode==="many"){this.grid._pagescache=new Array();this.grid._cellscache=new Array()}if(g!=null){if(g=="a"||g=="asc"||g=="ascending"||g==true){g=true}else{g=false}}var h=d;this.sortfield=d;this.sortfielddirection=g?"asc":"desc";if(g==null){this.sortfielddirection=null}if(this.sortcache==undefined){this.sortcache={}}this.sortdata=[];var b=[];var c=false;if(h=="constructor"){h=""}if(!this.virtualmode&&this.sortcache[h]!=null&&this.grid.sortmode!=="many"){var t=this.sortcache[h];b=t._sortdata;if(t.direction==g){b.reverse()}else{if(!t.direction&&g){b.reverse()}c=true}if(b.length<this.totalrecords){this.sortcache={};c=false;b=[]}}Object.prototype.toString=(typeof d=="function")?d:function(){return this[d]};var x=this.records;var z=this.that;var q="";if(this.source.datafields){a.each(this.source.datafields,function(){if(this.name==d){if(this.type){q=this.type}return false}})}if(b.length==0){if(x.length){var j=x.length;for(var w=0;w<j;w++){var f=x[w];if(f!=null){var k=f;var y=k.toString();b.push({sortkey:y,value:k,index:w})}}}else{var n=false;for(var s in x){var f=x[s];if(f==undefined){n=true;break}var k=f;b.push({sortkey:k.toString(),value:k,index:s})}if(n){a.each(x,function(A,B){b.push({sortkey:B.toString(),value:B,index:A})})}}}if(!c){if(u==null){this._sortcolumntype=q;var m=this;b.sort(function(A,i){return m._compare(A,i,q)})}else{b.sort(u)}}if(!g){b.reverse()}Object.prototype.toString=o;this.sortdata=b;if(g!==null){this.sortcache[h]={_sortdata:b,direction:g,dataType:this._sortcolumntype,dataField:h}}if(this.grid.sortmode==="many"){var v=[];var r=[];var e=[];for(var l in this.sortcache){if(l==="data"){continue}var t=this.sortcache[l];v.push(t.dataField);r.push(t.direction?"asc":"desc");e.push(t.dataType)}if(v.length>0){var p=this.multiSort(x,v,r,e);b=[];if(p){a.each(p,function(A,B){b.push({sortkey:h,value:B,index:B.boundindex})})}this.sortdata=b}else{this.sortcache={};this.sortdata=null;this.refresh();return}}this.reload(this.records,this.rows,this.filters,this.updated,true)},this.multiSort=function(g,c,k,m,f){var j=this;if(g.length===0){return[]}var b=[];for(var h=0;h<g.length;h++){var p=a.extend({},g[h]);b.push(p)}var l=false;if(b&&b.constructor&&b.constructor.name==="ObservableArray"){l=true}if(!b||!(b instanceof Array)||b.length===0||!c||c instanceof Array&&c.length===0){if(!l){throw new Error("sort: Missing or Invalid arguments!")}}if(typeof c==="string"){c=[c]}var e=[],o=[];if(k===undefined){k=[]}var d=function(i,s){var r=s||typeof i;var q;switch(r){case"string":default:q=new Intl.Collator().compare;break;case"date":case"time":case"datetime":case"number":case"int":case"float":q=function(u,t){return u-t};break;case"boolean":q=function(u,t){if(u===t){return 0}else{if(u===false){return -1}else{return 1}}};break;case"object":if(i instanceof Date){q=function(u,t){return u.getTime()-t.getTime()}}break}return q};for(var h=0;h<c.length;h++){if(k[h]===undefined||k[h]==="asc"||k[h]==="ascending"){e[h]=1}else{e[h]=-1}var n=b[0][c[h]];o[h]=d(n,m[h])}if(f){f(b,c,k,o);return}b.sort(function(s,r){for(var t=0;t<c.length;t++){var q=o[t](s[c[t]],r[c[t]]);if(q===0){if(c[t+1]){continue}else{if(s._index!==undefined){return(s._index-r._index)*e[t]}}return 0}return q*e[t]}if(c.length===0){if(s.boundIndex<r.boundIndex){return -1}if(s.boundIndex>r.boundIndex){return 1}return 0}});return b},this.clearsortdata=function(){this.sortcache={};this.sortdata=null};this._compare=function(c,b,e){var c=c.sortkey;var b=b.sortkey;if(c===undefined){c=null}if(b===undefined){b=null}if(c===null&&b===null){return 0}if(c===null&&b!==null){return -1}if(c!==null&&b===null){return 1}if(a.jqx.dataFormat){if(e&&e!=""){switch(e){case"number":case"int":case"float":if(c<b){return -1}if(c>b){return 1}return 0;case"date":case"time":if(c<b){return -1}if(c>b){return 1}return 0;case"string":case"text":c=String(c).toLowerCase();b=String(b).toLowerCase();break}}else{if(a.jqx.dataFormat.isNumber(c)&&a.jqx.dataFormat.isNumber(b)){if(c<b){return -1}if(c>b){return 1}return 0}else{if(a.jqx.dataFormat.isDate(c)&&a.jqx.dataFormat.isDate(b)){if(c<b){return -1}if(c>b){return 1}return 0}else{if(!a.jqx.dataFormat.isNumber(c)&&!a.jqx.dataFormat.isNumber(b)){c=String(c).toLowerCase();b=String(b).toLowerCase()}}}}}try{if(c<b){return -1}if(c>b){return 1}}catch(d){var f=d}return 0};this._equals=function(c,b){return(this._compare(c,b)===0)}};a.extend(a.jqx._jqxGrid.prototype,{_rendersortcolumn:function(){var b=this.that;var h=this.getsortcolumn();if(this.sortmode==="many"){this.__columnsbydatafield=new Array();var d=this.getsortcolumns();a.each(this.columns.records,function(j,k){a(this.sortasc).hide();a(this.sortdesc).hide();a(this.sorticon).css("visibility","hidden");this.element.removeAttribute("sort")});if(d.length>0){for(var c=0;c<d.length;c++){var e=d[c];var g=this._getcolumnbydatafield(e.dataField);g.element.setAttribute("sort",true);if(b.isMaterialized()||b.isModern()){a(g.sorticon).show();a(g.sorticon).css("visibility","inherit");a(g.sorticon).removeClass("ascending");a(g.sorticon).removeClass("descending");if(e.ascending){a(g.sorticon).addClass("ascending")}else{a(g.sorticon).addClass("descending")}}else{if(e.ascending){a(g.sortasc).show();a(g.sortdesc).hide()}else{if(e.ascending===false){a(g.sortasc).hide();a(g.sortdesc).show()}}}}}return}if(this.sortdirection){var f=function(j,k){var i=b.getcolumn(j);if(i){if(k.ascending){a.jqx.aria(i.element,"aria-sort","ascending")}else{if(k.descending){a.jqx.aria(i.element,"aria-sort","descending")}else{a.jqx.aria(i.element,"aria-sort","none")}}}};if(this._oldsortinfo){if(this._oldsortinfo.column){f(this._oldsortinfo.column,{ascending:false,descending:false})}}f(h,this.sortdirection)}this._oldsortinfo={column:h,direction:this.sortdirection};if(this.sortdirection){a.each(this.columns.records,function(k,l){var j=a.data(document.body,"groupsortelements"+this.displayfield);if(h==null||this.displayfield!=h){a(this.sortasc).hide();a(this.sortdesc).hide();a(this.sorticon).css("visibility","hidden");a(this.sorticon).removeClass("ascending");a(this.sorticon).removeClass("descending");this.element.removeAttribute("sort");if(j!=null){j.sortasc.hide();j.sortdesc.hide()}}else{if(b.isMaterialized()||b.isModern()){a(this.sortasc).hide();a(this.sortdesc).hide();a(this.sorticon).show();a(this.sorticon).css("visibility","inherit");if(j!=null){j.sortasc.hide();j.sortdesc.show()}a(this.sorticon).removeClass("ascending");a(this.sorticon).removeClass("descending");if(b.sortdirection.ascending){a(this.sorticon).addClass("ascending")}else{a(this.sorticon).addClass("descending")}}else{if(b.sortdirection.ascending){a(this.sortasc).show();a(this.sortdesc).hide();if(j!=null){j.sortasc.show();j.sortdesc.hide()}}else{a(this.sortasc).hide();a(this.sortdesc).show();if(j!=null){j.sortasc.hide();j.sortdesc.show()}}}this.element.setAttribute("sort",true)}})}},getsortcolumns:function(){var c=this;var b=[];for(var e in c.dataview.sortcache){if(e==="data"){continue}b.push({dataField:e,ascending:c.dataview.sortcache[e].direction})}if(b.length===0){for(var f in c.sortcolumns){var d=c.sortcolumns[f];if(d!==null){b.push({dataField:f,ascending:d})}}}return b},getsortcolumn:function(){if(this.sortcolumn!=undefined){return this.sortcolumn}return null},removesort:function(){this.sortby(null);if(this.sortmode==="many"){for(var b in this.sortcolumns){this.sortby(b,null)}}},sortby:function(d,h,g,f,b){if(this._loading&&b!==false){throw new Error("jqxGrid: "+this.loadingerrormessage);return false}if(d==null){h=null;d=this.sortcolumn}if(d!=undefined){var c=this.that;if(g==undefined&&c.source&&c.source.sortcomparer!=null){g=c.source.sortcomparer}if(h===undefined){h=true}if(h=="a"||h=="asc"||h=="ascending"||h==true){var e=true}else{var e=false}if(h!=null){c.sortdirection={ascending:e,descending:!e}}else{c.sortdirection={ascending:false,descending:false}}if(h!=null){c.sortcolumn=d}else{c.sortcolumn=null}if(!c.sortcolumns){c.sortcolumns=[]}c.sortcolumns[d]=h;if(c.source&&(c.source.sort||c.virtualmode)){c.dataview.sortfield=d;if(h==null){c.dataview.sortfielddirection=""}else{c.dataview.sortfielddirection=e?"asc":"desc"}if(c.source.sort&&!this._loading){c.source.sort(d,h,c.sortcolumns);c._raiseEvent(6,{sortinformation:c.getsortinformation()});return}}else{c.dataview.sortby(d,h,g)}if(f===false){return}if(c.groupable&&c.groups.length>0){c._render(true,false,false);if(c._updategroupheadersbounds&&c.showgroupsheader){c._updategroupheadersbounds()}c._postrender("sort")}else{if(c.pageable){c.dataview.updateview()}c._updaterowsproperties();c.rendergridcontent(true);c._postrender("sort")}c._raiseEvent(6,{sortinformation:c.getsortinformation()})}},_togglesort:function(f){var b=this.that;if(this.disabled){return}if(this.sortmode==="many"){if(f.sortable&&b.sortable){var e=b.getsortcolumns();var g=null;for(var d=0;d<e.length;d++){var j=e[d];var h=f.displayfield;if(h===j.dataField){g=j.ascending;if(b.sorttogglestates>1){if(g==true){g=false}else{g=null}}else{g=!g}b.sortby(f.displayfield,g,null);return}}b.sortby(f.displayfield,true,null)}return}if(f.sortable&&b.sortable){var c=b.getsortinformation();var g=null;if(c.sortcolumn!=null&&c.sortcolumn==f.displayfield){g=c.sortdirection.ascending;if(b.sorttogglestates>1){if(g==true){g=false}else{g=null}}else{g=!g}}else{g=true}b.sortby(f.displayfield,g,null)}}})})(jqxBaseFramework);

