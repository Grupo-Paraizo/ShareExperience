package com.example.sev1;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {
    //private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth referenciaAutenticacao;

    //retorna instancia do FirebaseAuth
    public static FirebaseAuth getFirebaseAutenticacao(){
        if(referenciaAutenticacao == null){
            referenciaAutenticacao = FirebaseAuth.getInstance();
        }
        return referenciaAutenticacao;
    }
}
