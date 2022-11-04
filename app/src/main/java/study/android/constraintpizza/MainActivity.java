package study.android.constraintpizza;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.transition.AutoTransition;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout layout;
    boolean moved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.root);

        findViewById(R.id.pizza).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!moved) animate(R.layout.activity_main2);
                else animate(R.layout.activity_main);
                moved = !moved;
            }
        });
    }

    void animate(int id) {
        ConstraintSet constraintSet = new ConstraintSet();
        Transition transition = new AutoTransition();
        transition.setDuration(1200);
        transition.setInterpolator(new AnticipateOvershootInterpolator());
//        transition.setInterpolator(new BounceInterpolator());
//        transition.setInterpolator(new CycleInterpolator(5));
        constraintSet.clone(this, id);
        TransitionManager.beginDelayedTransition(layout, transition);
        constraintSet.applyTo(layout);
    }
}