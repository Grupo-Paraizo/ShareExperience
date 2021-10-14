package com.example.sev1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button botaoLogin;
    private ProgressBar progressBar;

    private Usuario usuario;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //verificarUsuarioLogado();
        inicializarComponentes();
        progressBar.setVisibility(View.GONE);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                if(!textoEmail.isEmpty()){
                    if(!textoSenha.isEmpty()){

                        usuario = new Usuario();
                        usuario.setEmail(textoEmail);
                        usuario.setSenha(textoSenha);
                        validarLogin(usuario);

                    } else{
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this,
                                "Preencha o sua senha!",
                                Toast.LENGTH_SHORT).show();
                    }

                } else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this,
                            "Preencha o seu email!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verificarUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        if(autenticacao.getCurrentUser() != null){
            Toast.makeText(LoginActivity.this,
                    autenticacao.getCurrentUser().toString(),
                    Toast.LENGTH_LONG).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    public void validarLogin(Usuario usuario){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this,
                            "Erro ao fazer login!",
                            Toast.LENGTH_SHORT).show();

                } else{
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }

    public void abrirCadastro(View view){
        Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(i);
    }

    public void resetPassword(View view){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        if (!campoEmail.getText().toString().isEmpty()) {
            autenticacao.sendPasswordResetEmail(campoEmail.getText().toString())
                    .continueWith(task -> {
                        progressBar.setVisibility(View.VISIBLE);
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this,
                            "Um email foi enviado para resetar sua senha!",
                            Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(LoginActivity.this,
                            "Não foi possivel enviar email!",
                            Toast.LENGTH_SHORT).show();
                }
                campoSenha.setText("");
                progressBar.setVisibility(View.GONE);
                return null;
            });
        } else{
            Toast.makeText(LoginActivity.this,
                    "Por favor, digite um email!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void inicializarComponentes(){
        campoEmail = findViewById(R.id.email_login);
        campoSenha = findViewById(R.id.senha_login);
        botaoLogin = findViewById(R.id.btn_logar);
        progressBar = findViewById(R.id.progress_login);

        campoEmail.requestFocus();
    }
}