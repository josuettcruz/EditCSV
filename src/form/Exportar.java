/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form;

import file.*;
import java.util.ArrayList;
import java.util.List;

public class Exportar {
    
    private csv code;
    
    private String host;
    
    private boolean tag;
    
    public Exportar(csv code, String sent){
        
        this.code = code;
        
        this.host = sent;
        
        this.tag = false;
        
    }
    
    private String Number(int numb){
        
        int num = numb + 1;
        
        String txt = "";
        
        if(num < 10 && this.code.Tot() >= 10){
            txt += "0";
        }
        
        if(num < 100 && this.code.Tot() >= 100){
            txt += "0";
        }
        
        if(num < 1000 && this.code.Tot() >= 1000){
            txt += "0";
        }
        
        txt += num;
        
        return txt;
        
    }
    
    /*private String Tag(String dig){
        
        String txt = "";
        
        boolean metatag = false;
        
        for(int i = 0; i < dig.length(); i++){
            
            char ds = dig.charAt(i);
            
            switch(ds){
                
                case '<':
                this.tag = true;
                if(!metatag){
                    txt += "<span>";
                    metatag = true;
                }
                break;
                
                case '>':
                if(metatag){
                    txt += "</span>";
                    metatag = false;
                }
                break;
                
                default:
                txt += ds;
                break;
                
            }

        }
        
        if(metatag){
            txt += "</span>";
        }

        return txt;

    }/*Tag(String dig)*/
    
    private String T(String text){
        
        String dig = text.replace(" | ", "\\");
        
        String txt = "";
        
        boolean place = false;
        
        boolean aspas = true;
        
        boolean letter = true;
        
        boolean metatag = false;
        
        for(int i = 0; i < dig.length(); i++){
            
            char ds = dig.charAt(i);
            
            switch(ds){
                
                case ')':
                case '}':
                case ']':
                case '!':
                case '?':
                case '.':
                txt += ds;
                place = true;
                if(!metatag){letter = true;}
                break;
                
                case '\"':
                    
                    if(metatag){
                        txt += "\"";
                    }
                    
                    if(letter){
                        
                        if(aspas){
                            
                            txt += "<q>";
                            aspas = false;
                            
                        } else {
                            
                            txt += "</q>";
                            aspas = true;
                            
                        }
                        
                    }
                    
                    letter = false;
                    
                break;
                
                case '<':
                this.tag = true;
                if(!metatag){
                    txt += "<span>";
                    place = false;
                    letter = true;
                    metatag = true;
                }
                letter = false;
                break;
                
                case '>':
                if(metatag){
                    txt += "</span>";
                    place = false;
                    letter = true;
                    metatag = false;
                }
                letter = true;
                break;
                
                case ' ':
                    
                    if(i == 0){
                        
                        txt += "-";
                        place = false;
                        
                    } else if(i == dig.length()-1){
                        
                        txt += ".";
                        place = false;
                        
                    } else if(place){
                        
                        txt += "<br/>";
                        place = false;
                        
                    } else {
                        
                        txt += " ";
                        
                    }
                    
                break;
                
                case '\\':
                txt += "<br/>";
                place = false;
                if(!metatag){letter = true;}
                break;
                
                default:
                if(i == 0 && dig.contains(" ")){
                    String done = ds + "";
                    txt += done.toUpperCase();
                } else {
                    txt += ds;
                }
                place = false;
                if(!metatag){letter = true;}
                break;
                
            }

        }
        
        if(!aspas){
            
            txt += "</q>";
            
        }
        
        if(metatag){
            
            txt += "</span>";
            
        }

        return txt;

    }//T(String dig)
    
    private String P(String paragraphy){
        
        cod p = new cod();
        
        boolean data = p.Date(paragraphy).isBlank();
        
        if(data){
            
            return "<p class=\"texto\">" + p.Date() + "</p>";
            
        } else {
        
            return "<p class=\"texto\">" + T(paragraphy) + "</p>";
        
        }
        
    }//P(String paragraphy)
    
    private String P(String paragraphy, String link){
        
        String txt = "<p class=\"texto\"><a href=\"";
        txt += link;
        txt += "\" target=\"_blank\">";
        
        cod lm = new cod();
        
        boolean datado = lm.Date(paragraphy).isBlank();
        
        if(datado){
            
            txt += lm.Date();
            
        } else {
            
            txt += T(paragraphy);
            
        }
        
        txt += "</a></p>";
        
        
        return txt;
        
    }//P(String paragraphy, String link)
    
    public void Export(String title,String footer, String find){
        
        cod c = new cod();
        
        List<String> doc = new ArrayList();
        
        boolean cd = this.code.Tot() > 0;
        
        String select_title;
        
        if(find.length() <= 20 && !find.contains(" ")){
            select_title = find;
        } else {
            select_title = title;
        }
        
        doc.add("<html>");
        doc.add("<head>");
        doc.add("<title>" + select_title + "</title>");
        doc.add("<meta charset=\"utf-8\" />");
        doc.add("<!--<link rel=\"icon\" href=\"pasta\\arquivo.ico\" type=\"image/x-icon\">-->");
        doc.add("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        doc.add("<style>");
        
        if(cd){
            
            doc.add("   a:link{");
            doc.add("      color: inherit;");
            doc.add("      text-decoration: overline;");
            doc.add("      text-decoration-color: rgb(0,0,0);");
            doc.add("   }");
            
            doc.add("   ");
            
            doc.add("   a:hover{");
            doc.add("     color: inherit;");
            doc.add("     text-decoration: overline;");
            doc.add("     text-decoration-color: rgb(100,100,100);");
            doc.add("   }");
            
            doc.add("   ");
            
            doc.add("   a:active{");
            doc.add("      color: inherit;");
            doc.add("      text-decoration: none;");
            doc.add("   }");
            
            doc.add("   ");
            
            doc.add("   a:visited{");
            doc.add("      color: inherit;");
            doc.add("      text-decoration: overline;");
            doc.add("      text-decoration-color: rgb(100,100,100);");
            doc.add("   }");
            
            doc.add("   ");
            
            doc.add("   div.txt{");
            doc.add("      margin-left:5%;");
            doc.add("      margin-top:10%;");
            doc.add("      width:90%;");
            doc.add("      border: 5px solid black;");
            doc.add("      background-color:whitesmoke;");
            doc.add("      min-height:100px;");
            doc.add("      overflow-y:visible;");
            doc.add("   }");
            
            doc.add("   ");
            
            doc.add("   div.space{");
            doc.add("      width:100%;");
            doc.add("      height:2px;");
            doc.add("      background-color:black;");
            doc.add("   }");
            
            doc.add("   h1.tema{");
            doc.add("      color:black;");
            doc.add("      margin-left:2.5%;");
            doc.add("      font-size:calc(10px + 2vw);");
            doc.add("   }");
            
            doc.add("   ");
            
            doc.add("   p.texto{");
            doc.add("      color:black;");
            doc.add("      font-weight: bold;");
            doc.add("      margin-top:10px;");
            doc.add("      margin-left:2%;");
            doc.add("      margin-right:1%;");
            doc.add("      font-size:calc(10px + 1vw);");
            doc.add("      word-wrap: break-word;");
            doc.add("      line-height:2em;");
            doc.add("   }");
            
            doc.add("   ");
            
            doc.add("   div.divide{");
            doc.add("      width:100%;");
            doc.add("      height:20px;");
            doc.add("      background-color:rgba(0, 0, 0, .01);");
            doc.add("   }");
            
            doc.add("   ");
            
            doc.add("   p.ended{");
            doc.add("      padding:50px;");
            doc.add("   }");
            
        } else {
            
            doc.add("div.txt{");
            doc.add("  margin-top:20%;");
            doc.add("  margin-left:5%;");
            doc.add("  font-size:7vw;");
            doc.add("}");
            
        }
        
        doc.add("</style>");
        doc.add("</head>");
        doc.add("<body>");
        doc.add("");
        
        if(cd){//if(cd) - 1
            
            for(int x = 0; x < this.code.Tot(); x++){
                
                String tx = "";
                
                tx += "<div class=\"txt\">";
                
                for(int y = 0; y < this.code.Tot(x); y++){
                    
                    if(y == 0){
                        
                        tx += "<h1 class=\"tema\">";
                        tx += this.code.Read(x, 0).replace(" | ", "<br/>");
                        tx += "</h1>";
                        
                    } else {//if(y == 0)
                        
                        if(c.Link(this.code.Read(x, y))){
                            
                            tx += "<div class=\"space\"></div>";
                            
                            if(c.Link(this.code.Read(x, y-1))){
                                
                                tx += P(this.code.Read(x, y),this.code.Read(x, y));
                                
                            } else {//if(c.Link(this.code.Read(x, y-1)))
                                
                                tx += P(this.code.Read(x, y-1),this.code.Read(x, y));
                                
                            }//if(c.Link(this.code.Read(x, y-1)))
                            
                        } else if(y == this.code.Tot(x)-1){//if(c.Link(this.code.Read(x, y)))
                            
                            tx += "<div class=\"space\"></div>";
                            tx += P(this.code.Read(x, y));
                            
                        } else {//if(c.Link(this.code.Read(x, y)))
                            
                            if(!c.Link(this.code.Read(x, y+1))){
                                
                                tx += "<div class=\"space\"></div>";
                                tx += P(this.code.Read(x, y));
                                
                            }//if(!c.Link(this.code.Read(x, y+1)))
                            
                        }//if(c.Link(this.code.Read(x, y)))
                        
                    }//if(y == 0)
                    
                }//for(int y = 0; y < this.code.Tot(x); y++)
                
                doc.add(tx + "<div class=\"divide\"></div></div>");
                
                doc.add("<!-- " + 
                        Number(x) + 
                        " de " + 
                        this.code.Tot() + " -->");
                
                doc.add("");
                
            }//for(int x = 0; x < this.code.Tot(); x++)
            
            doc.add("<p class=\"ended\"></p>");
            
            if(this.tag){
                
                doc.add("");
                doc.add("<script>");
                doc.add("   ");
                doc.add("   const metatag = document.getElementsByTagName(\"span\");");
                doc.add("   ");
                doc.add("   for(var i = 0; i < metatag.length; i++){");
                doc.add("      ");
                doc.add("      metatag[i].innerText = \"<\" + metatag[i].innerHTML + \">\";");
                doc.add("      metatag[i].style.fontWeight = \"normal\";");
                doc.add("      metatag[i].style.fontFamily = \"Arial Narrow\";");
                doc.add("      metatag[i].style.letterSpacing = \"1%\";");
                doc.add("      ");
                doc.add("   }");
                doc.add("   ");
                doc.add("</script>");
                
            }//if(this.tag)
            
        } else {//if(cd) - 1
            
            doc.add("<div class=\"txt\">" + find + "</div>");
            
        }//if(cd) - 1
        
        doc.add("");
        doc.add("</body>");
        doc.add("</html>");
        
        if(cd){//if(cd) - 2
            
            doc.add("");
            
            doc.add("<!-- " + footer + " --");
            
            for(int x = 0; x < this.code.Tot(); x++){
                
                String tx = "";
                
                for(int y = 0; y < this.code.Tot(x); y++){
                    
                    if(y > 0){
                        tx += ";";
                    }
                    
                    tx += this.code.Read(x, y);
                    
                }//for(int y = 0; y < this.code.Tot(x); y++)
                
                doc.add(tx);
                
            }//for(int x = 0; x < this.code.Tot(); x++)
            
        }//if(cd) - 2
        
        cod cod = new cod();
        
        Files line_page = new Files(this.host + 
                "Export_" +
                cod.Date(false) + 
                "_" + 
                cod.Time(false) + 
                ".htm");
        
        line_page.Clear();
        line_page.WriteAll(doc);
        
        doc.clear();
        
    }//Export(String title, boolean target)
    
}//Exportar