package util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    //static faz o metodo se tornar um metodo de classe 
    //voce pode chamar ele quando chama a classe , sem precisar criar um objeto 
    public static byte[] criptografarSenha(String senha) {
        return encoder.encode(senha).getBytes();
    }

    public static boolean verificarSenha(String senhaDigitada, String senhaArmazenada) {
        return encoder.matches(senhaDigitada, senhaArmazenada);
    }
    
    public static String verificarSenhaForte(String senha, String repitaSenha) {
        StringBuilder mensagem = new StringBuilder();

        // Verifica se a senha tem pelo menos 8 caracteres
        if (senha.length() < 8) {
            mensagem.append("Mínimo 8 caracteres.\n");
        }

        // Verifica se a senha contém pelo menos uma letra maiúscula
        if (!senha.matches(".*[A-Z].*")) {
            mensagem.append("Uma letra maiúscula.\n");
        }

        // Verifica se a senha contém pelo menos uma letra minúscula
        if (!senha.matches(".*[a-z].*")) {
            mensagem.append("Uma letra minúscula.\n");
        }

        // Verifica se a senha contém pelo menos um dígito
        if (!senha.matches(".*\\d.*")) {
            mensagem.append("Pelo menos um dígito.\n");
        }

        // Verifica se a senha contém pelo menos um caractere especial
        if (!senha.matches(".*[\\W_].*")) {
            mensagem.append("Um caractere especial.\n");
        }

        // Verifica se as senhas são iguais
        if (!senha.equals(repitaSenha)) {
            mensagem.append("As senhas não são iguais.\n");
        }
        return mensagem.toString();
    }
}