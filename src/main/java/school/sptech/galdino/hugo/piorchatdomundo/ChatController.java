package school.sptech.galdino.hugo.piorchatdomundo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {
    String mensagens = "";

    @GetMapping("/conversa")
    public String chat(){
        return """
                <!DOCTYPE html>
                <html lang="pt-BR">
                                
                <head>
                    <meta charset="UTF-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Chat</title>
                    <style>
                        body {
                            height: 100%;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            min-height: 100vh;
                        }
                                
                        .container {
                            background-color: aqua;
                            width: 50em;
                            height: 70vh;
                            border: 2px solid black;
                        }
                                
                        .msg-history {
                            background-color: orange;
                            width: 100%;
                            height: 80%;
                            box-sizing: border-box;
                            border: 2px solid black;
                            padding: 2em;
                            overflow: scroll;
                        }
                                
                        .bottom-container {
                            display: flex;
                            height: 20%;
                        }
                                
                        .name-container {
                            background-color: blue;
                            height: 100%;
                            width: 15%;
                            box-sizing: border-box;
                            border-right: 1px solid black;
                        }
                                
                        .message-container {
                            background-color: blueviolet;
                            height: 100%;
                            width: 85%;
                            box-sizing: border-box;
                            display: flex;
                            align-items: center;
                            justify-content: space-around;
                        }
                                
                        #message-box {
                            resize: none;
                            width: 70%;
                            height: 70%;
                        }
                                
                        .name-container {
                            display: flex;
                            flex-direction: column;
                            justify-content: center;
                        }
                    </style>
                </head>
                                
                <body>
                    <div class="container">
                        <div class="msg-history">
                                """ + mensagens +"""
                        </div>
                        <div class="bottom-container">
                            <div class="name-container">
                                <input type="text" name="ipt-nome" id="ipt-nome" placeholder="Nome">
                                <div class="ipt-habilitado__container">
                                    <input type="checkbox" name="ipt-habilitado" id="ipt-habilitado">
                                    <label for="ipt-habilitado">Habilitado</label>
                                </div>
                            </div>
                            <div class="message-container">
                                <textarea name="message-box" id="message-box"></textarea>
                                <input type="button" value="Enviar" id="btn-enviar">
                            </div>
                        </div>
                    </div>
                </body>
                <script>
                    let checkboxNome = document.getElementById("ipt-habilitado");
                    let caixaNome = document.getElementById("ipt-nome");
                    
                    checkboxNome.checked = true;
                    caixaNome.disabled = false;
                    
                    checkboxNome.addEventListener('change', function () {
                        if (this.checked) {
                            caixaNome.disabled = false;
                        } else {
                            caixaNome.disabled = true;
                        }
                    })
                                
                    let btnEnviar = document.getElementById("btn-enviar");
                    btnEnviar.addEventListener('click', function () {
                        let conteudoMensagem = document.getElementById("message-box").value;
                        let conteudoNome = document.getElementById("ipt-nome").value;
                        let url = "./enviar?nome=" + encodeURIComponent(conteudoNome) + "&mensagem=" + encodeURIComponent(conteudoMensagem);
                        window.location.href = url
                    })
                </script>
                                
                </html>
                """;
    }

    @GetMapping("/enviar")
    @ResponseBody
    public String enviar(@RequestParam String nome, @RequestParam String mensagem){
        mensagens += "<b>" + nome + ": </b>" + mensagem + "<br>";
        return """
                <!DOCTYPE html>
                <html lang="pt-BR">
                                
                <head>
                    <meta charset="UTF-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Enviando</title>
                </head>
                                
                <body>
                                
                </body>
                <script>
                    // Get the value of the "text" GET parameter from the URL
                    var urlParams = new URLSearchParams(window.location.search);
                    let nomeParam = urlParams.get('nome');
                    let mensagemParam = urlParams.get('mensagem');
                    var textParam = urlParams.get('text');
                                
                    // Decode the value using decodeURIComponent()
                    //var decodedNome = decodeURIComponent(textParam);
                    let nome = decodeURIComponent(nomeParam);
                    let mensagem = decodeURIComponent(mensagemParam);
                                
                    
                    window.location.href = "./conversa"
                </script>
                                
                </html>
                """;
    }
}
