package br.com.danielarocha.AppCalculadora.resource;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//aqui informo que é uma controller
@Controller
public class WebController {

  //uso getmapping para dizer que estou pegando a informação
  @GetMapping("/")
  public String showForm(Model model)
  //aqui crio uma showform e digo para o front que vai ter duas operações
  {
    model.addAttribute("modelOperacoes",List.of("Soma","Subtracao"));
    return "form";
  }

  // esta barra ("/")é a barra do navegador
  //toda vez que usamos o Mapping significa que é um verbo uma ação , esta mandando o computador fazer alguma coisa, pegar, criar, postar
  @PostMapping("/")   //esta funcao é para a hora que chamar na tela executar vem nesse funcao e executa
  public String handleFormSubmission(@RequestParam String modelOperacoes, //AQUI EU USO PARA DIZER QUE VOU PASSAR OS PARAMETROS
                                     @RequestParam String valor01,
                                     @RequestParam String valor02,
                                     Model model)
{
  //aqui eu fiz um verificacao que diz se algum dos meus valores forem zero(nulo)vazio não faço nada 
  if(modelOperacoes.isEmpty() || valor01.isEmpty() || valor02.isEmpty())
  return null;

  //apos a verificacao de que não é vazio eu faço o switch case
  //aqui eu faço o switch para receber um ou outro e fazer a açao 
  String resposta = "";
  int resp = 0;
  switch (modelOperacoes) {
    //aqui diz se o modelo for uma soma eu faço a soma
    case "Soma":
    //aqui eu suo o parseInt porque meu parametro la em cima (@RequestParam String valor01)é texto e isso vai converte em numero 
       resp = Integer.parseInt(valor01) + Integer.parseInt(valor02);
       resposta =String.valueOf(resp);
      break;
   case "Subtracao":
   //se o modelo for uma subtracao eu faco uma subtracao
       resp = Integer.parseInt(valor01) - Integer.parseInt(valor02);
      break;
  }
  //depois de fazer toda a operacao la em cima eu pego converto para texto(resposta = String.valueOf(resp);novamente e passo a resposta 
  resposta = String.valueOf(resp);

  //agora eu devolvo a resposta para a tela 
  model.addAttribute("modelOperacoes",List.of("Soma","Subtracao"));
  model.addAttribute("response",resposta);
  model.addAttribute("selectedModel", modelOperacoes);
  return "form";
}

}
