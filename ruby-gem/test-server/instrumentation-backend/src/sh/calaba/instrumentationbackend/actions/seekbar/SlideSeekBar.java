package sh.calaba.instrumentationbackend.actions.seekbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.actions.Action;


public class SlideSeekBar implements Action {

    @Override
    public Result execute(String... args) {
    	View textView = InstrumentationBackend.solo.getText(args[0]);
		ViewGroup parentView = (ViewGroup) textView.getParent();
		int i=0;
		for(; i<parentView.getChildCount(); i++) {
			if (textView.equals(parentView.getChildAt(i))) {
				break;
			}
		}
		
		//either get the previous or next view
		if (args[2] != null && "previous".equals(args[2])) {
			i--;
		} else {
			i++;
		}
		
		ViewGroup sliderContainer = (ViewGroup) parentView.getChildAt(i); 
		SeekBar seekbar = (SeekBar) sliderContainer.getChildAt(0);
		seekbar.setProgress(Integer.parseInt(args[1]));
		return Result.successResult();
    }

    @Override
    public String key() {
        return "slide_seek_bar";
    }

}
