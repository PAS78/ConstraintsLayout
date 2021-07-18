package study.android.constraintpizza;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AnticipateOvershootInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.transition.AutoTransition;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

public class Result extends AppCompatActivity {
    ConstraintLayout layout;
    boolean moved = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.s1);
        layout = findViewById(R.id.root);
        findViewById(R.id.pizza).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (!moved) animate(R.layout.s1);
               else animate(R.layout.s2);
               moved = !moved;
            }
        });
    }
    void animate(int id){
        ConstraintSet cs = new ConstraintSet();
        Transition transition = new AutoTransition();
        transition.setDuration(700);
        transition.setInterpolator(new AnticipateOvershootInterpolator());
        cs.clone(this, id);
        TransitionManager.beginDelayedTransition(layout, transition);
        cs.applyTo(layout);
    }
}
