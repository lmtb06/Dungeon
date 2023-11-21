package com.dungeondevs.components.rendering;

import com.artemis.Component;
import com.dungeondevs.utils.AnimationData;

import java.util.ArrayList;
import java.util.List;

public class AnimationListComponent extends Component {
    private int currentAnimation = 0;
    private List<AnimationData> animations = new ArrayList<>();

    public void addAnimationData (AnimationData animationData) {
        animations.add(animationData);
    }

    public AnimationData getCurrentAnimationData () {
        return animations.get(currentAnimation);
    }

    public void setCurrentAnimation(int animation){
        if(animation < 0 || animation > animations.size())
            throw new ArrayIndexOutOfBoundsException();
        else
            currentAnimation = animation;
    }
}
