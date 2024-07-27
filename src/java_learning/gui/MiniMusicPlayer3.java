package java_learning.gui;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

@SuppressWarnings({"all"})
public class MiniMusicPlayer3 {
    static JFrame f = new JFrame("My First Music Video");
    static MyDrawPanel2 m1;

    public static void main(String[] args) {
        MiniMusicPlayer3 mini = new MiniMusicPlayer3();
        mini.go();
    }

    public void setUpGui() {
        m1 = new MyDrawPanel2();
        f.setContentPane(m1);
        f.setBounds(30, 30, 300, 300);
        f.setVisible(true);
    }

    public void go() {
        setUpGui();

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addControllerEventListener(m1, new int[]{127});
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            int r = 0;
            for (int i = 0; i < 60; i += 4) {
                r = (int) ((Math.random() * 50) + 1);
                track.add(makeEvent(144, 1, r, 100, i));
                track.add(makeEvent(176, 1, 127, 0, i));
                track.add(makeEvent(128, 1, r, 100, i + 2));
            }

            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(120);
            sequencer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return event;
    }
}

class MyDrawPanel2 extends JPanel implements ControllerEventListener {
    boolean mag = false;

    @Override
    public void controlChange(ShortMessage event) {
        mag = true;
        repaint();
    }

    @Override
    public void paintComponents(Graphics g) {
        if (mag) {
            Graphics2D g2d = (Graphics2D) g;

            int red = (int) (Math.random() * 250);
            int green = (int) (Math.random() * 250);
            int blue = (int) (Math.random() * 250);
            g2d.setColor(new Color(red, green, blue));

            int height = (int) ((Math.random() * 120) + 10);
            int width = (int) ((Math.random() * 120) + 10);
            int x = (int) ((Math.random() * 40) + 10);
            int y = (int) ((Math.random() * 40) + 10);
            g2d.fillRect(x, y, width, height);

            mag = false;
        }
    }
}