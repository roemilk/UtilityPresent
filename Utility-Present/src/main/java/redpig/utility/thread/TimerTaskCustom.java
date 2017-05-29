package redpig.utility.thread;

import java.util.TimerTask;

/**
 * Created by Kim YoungHun on 2016-05-20.
 * 기존의 TimerTask클래스를 상속하여 현재 타이머가 실행중인지 체크가 가능하게
 * isRunning메서드가 추가되었습니다.
 */
public class TimerTaskCustom extends TimerTask {
    private boolean isRunning = false;
    @Override
    public void run() {
        isRunning = true;
    }

    /**
     * 현재 타이머가 동작중인지에 대한 상태값을 boolean 형태로 리턴합니다.
     * @return boolean
     */
    public boolean isRunningTask(){
        return this.isRunning;
    }
}
