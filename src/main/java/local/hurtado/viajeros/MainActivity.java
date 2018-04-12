package local.hurtado.viajeros;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //FacebookSdk.sdkInitialize(getApplicationContext());
            setContentView(R.layout.activity_main);

            mDatabase = FirebaseDatabase.getInstance().getReference("/pais");

            ValueEventListener listener = new ValueEventListener() {
                String TAG;
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    GenericTypeIndicator<List<pais>> p = new GenericTypeIndicator <List<pais>>() {};
                    List<pais> camboya =  dataSnapshot.getValue(p);

                    Contenido contenidoInfo = new Contenido("Información general", camboya.get(0).getGeneral());
                    SingletonContenido.get(getApplicationContext()).getmContenido(getApplicationContext()).add(contenidoInfo);

                    Contenido contenidoClima = new Contenido("Clima", camboya.get(0).getClima());
                    SingletonContenido.get(getApplicationContext()).getmContenido(getApplicationContext()).add(contenidoClima);

                    Contenido contenidoMoneda = new Contenido("Moneda", camboya.get(0).getMoneda());
                    SingletonContenido.get(getApplicationContext()).getmContenido(getApplicationContext()).add(contenidoMoneda);

                    Contenido contenidoReligion = new Contenido("Religion", camboya.get(0).getCultura_religion());
                    SingletonContenido.get(getApplicationContext()).getmContenido(getApplicationContext()).add(contenidoReligion);

                    Contenido contenidoTransporte = new Contenido("Transporte", camboya.get(0).getTransporte());
                    SingletonContenido.get(getApplicationContext()).getmContenido(getApplicationContext()).add(contenidoTransporte);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                }
            };

            mDatabase.addValueEventListener(listener);

            Fragment fragment = new ListadoActivity();
            cargarFragment(fragment);

        }

        public void cargarFragment(Fragment frag) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.contenedor, frag).commit();
        }






    /**
    SignInButton loguin_btt;
    private static final int RC_SIGN_IN = 1;
    private GoogleApiClient mGoogleApiClient;
    private static final String TAG = "MAINACTIVITY";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    LoginButton loginButton;
    CallbackManager callbackManager;

    EditText email, password;
    Button logMail, entrarMail;
    String mail, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        setContentView(R.layout.activity_main);


        email = (EditText) findViewById(R.id.edt_usuario);
        password = (EditText) findViewById(R.id.edt_password);
        logMail = (Button) findViewById(R.id.btt_entrar);
        entrarMail = (Button) findViewById(R.id.btt_logCreado);
        loguin_btt = (SignInButton) findViewById(R.id.btt_google);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()!= null) {
                    FragmentManager fm = getSupportFragmentManager();
                    Fragment fm_listado = new ListadoActivity();
                    //fm.beginTransaction(R.id.fm_listado).commit();
                    //Intent intent = new Intent(MainActivity.this, ListadoActivity.class);
                    //startActivity(intent);
                }
            }
        };


        //Configure Mail Sign In
        logMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail = ""+email.getText();
                pwd = ""+password.getText();
                mAuth.createUserWithEmailAndPassword(mail, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(MainActivity.this, ListadoActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().toString() ,Toast.LENGTH_SHORT);
                        }
                    }
                });
            }
        });

        entrarMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail = ""+email.getText();
                pwd = ""+password.getText();
                mAuth.signInWithEmailAndPassword(mail, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(MainActivity.this, ListadoActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().toString() ,Toast.LENGTH_SHORT);
                        }
                    }
                });
            }
        });

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getApplicationContext(), connectionResult.getErrorMessage(),Toast.LENGTH_SHORT);
                    }
                }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

        loguin_btt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_bttFacebook);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
                Intent intent = new Intent(MainActivity.this, ListadoActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Se canceló el inicio de sesión", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void handleFacebookAccessToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Error firebase login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    */
}

