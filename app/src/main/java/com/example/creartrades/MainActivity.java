package com.example.creartrades;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creartrades.config.Constantes;
import com.example.creartrades.databinding.ActivityMainBinding;
import com.example.creartrades.modelos.Trade;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Trade> listaTrades;

    private ActivityResultLauncher<Intent> crearTradeLaunch;
    private ActivityResultLauncher<Intent> editarTradeLaunch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listaTrades = new ArrayList<>();
        inicializaLaunchers();

        setSupportActionBar(binding.toolbar);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 crearTradeLaunch.launch(new Intent(MainActivity.this, AddTradeActivity.class));
            }
        });
    }

    private void inicializaLaunchers() {
        crearTradeLaunch = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            if (result.getData() != null && result.getData().getExtras() != null){
                                Trade trade = (Trade) result.getData().getExtras().getSerializable(Constantes.TRADE);
                                //Toast.makeText(MainActivity.this, "Se ha creado un trade", Toast.LENGTH_SHORT).show();
                                listaTrades.add(trade);
                                //Toast.makeText(MainActivity.this, "Se ha añadido a la lista", Toast.LENGTH_SHORT).show();
                                mostrarTrades();
                                //Toast.makeText(MainActivity.this, "Se ha mostrado el contenido", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "Ventana Cancelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        editarTradeLaunch = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            if (result.getData() != null && result.getData().getExtras() != null){
                                Trade trade = (Trade) result.getData().getExtras().getSerializable(Constantes.TRADE);
                                int posicion = result.getData().getExtras().getInt(Constantes.POSICION);
                                listaTrades.set(posicion, trade);
                                mostrarTrades();
                            }
                        }
                    }
                }
        );
    }

    private void mostrarTrades() {
        binding.contentMain.contenedorMain.removeAllViews();

        for (int i = 0; i < listaTrades.size(); i++) {
            Trade trade = listaTrades.get(i);
            View tradeView = LayoutInflater.from(MainActivity.this).inflate(R.layout.trade_model_view, null);
            TextView lblEstado = tradeView.findViewById(R.id.lblEstadoTradeModelView);
            TextView lblEntrada = tradeView.findViewById(R.id.lblEntradaTradeModelView);
            TextView lblAnyo = tradeView.findViewById(R.id.lblAnyoTradeModelView);
            TextView lblDia = tradeView.findViewById(R.id.lblDiaTradeModelView);
            TextView lblMes = tradeView.findViewById(R.id.lblMesTradeModelView);
            TextView lblMercado = tradeView.findViewById(R.id.lblMercadoTradeModelView);
            TextView lblValorPunto = tradeView.findViewById(R.id.lblValorPuntoTradeModelView);
            TextView lblContratos = tradeView.findViewById(R.id.lblContratosTradeModelView);
            TextView lblPuntos = tradeView.findViewById(R.id.lblPuntosTradeModelView);
            TextView lblEmocion = tradeView.findViewById(R.id.lblEmocionTradeModelView);
            Button btnEditarTrade = tradeView.findViewById(R.id.btnEditarTradeModelView);

            lblEstado.setText(trade.getEstado());
            lblEntrada.setText(trade.getEntrada());
            lblAnyo.setText(String.valueOf(trade.getAnyo()));
            lblDia.setText(String.valueOf(trade.getDia()));
            lblMes.setText(trade.getMes());
            lblMercado.setText(trade.getMercado());
            lblValorPunto.setText(String.valueOf(trade.getValorPunto()));
            lblContratos.setText(String.valueOf(trade.getContratos()));
            lblPuntos.setText(String.valueOf(trade.getPuntos()));
            lblEmocion.setText(trade.getEmocion());

            int finalI = i;
            btnEditarTrade.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constantes.TRADE, trade);
                    bundle.putInt(Constantes.POSICION, finalI);
                    intent.putExtras(bundle);
                    editarTradeLaunch.launch(intent);
                }
            });

            binding.contentMain.contenedorMain.addView(tradeView);
        }
    }
}