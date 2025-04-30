package com.g15.library_system.view.overrideComponent.searchFieldOption;

import com.g15.library_system.view.Style;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 * how to use:
 * TextFieldSearchOption txt = new TextFieldSearchOption();
 *     txt.setPreferredSize(new Dimension(300, 40));
 *     txt.addEventOptionSelected((option, index) ->
 *         txt.setHint("Search by " + option.getName() + "..."));
 *
 *     txt.addOption(new SearchOption("Name", new ImageIcon(getClass().getResource("/icons/searchOptionIcons/user.png"))));
 *     txt.addOption(new SearchOption("Tel", new ImageIcon(getClass().getResource("/icons/searchOptionIcons/tel.png"))));
 *     txt.addOption(new SearchOption("Email", new ImageIcon(getClass().getResource("/icons/searchOptionIcons/email.png"))));
 *     txt.addOption(new SearchOption("Address", new ImageIcon(getClass().getResource("/icons/searchOptionIcons/address.png"))));
 */


public class TextFieldSearchOption extends JTextField {
    private Animator animator;
    private float animate;
    private boolean option = false;
    private Shape shape;
    private boolean mousePressed = false;
    private final List<SearchOption> items = new ArrayList<>();
    private final List<SearchOptionEvent> events = new ArrayList<>();
    private int selectedIndex = -1;
    private int pressedIndex = -1;
    private Color colorOverlay1 = new Color(40, 170, 240);
    private Color colorOverlay2 = new Color(138, 39, 232);
    private String hint = "Search...";
    private Font font = Style.FONT_SANSERIF_PLAIN_16;
    private Color focusBorderColor = new Color(60, 158, 255);
    private Color normalBorderColor = new Color(150, 150, 150);
    private Color borderColor = normalBorderColor;
    private final int arc = 15;

    public TextFieldSearchOption() {
        setBorder(new EmptyBorder(8, 35, 8, 8));
        setFont(font);
        setOpaque(false);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setCaretColor(Color.BLACK);
        setSelectionColor(new Color(25, 141, 255));
        MouseAdapter mouseEvent = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                if (isOver(me.getPoint())) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                } else {
                    if (option) {
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                    } else {
                        setCursor(new Cursor(Cursor.TEXT_CURSOR));
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    mousePressed = isOver(me.getPoint());
                    if (!mousePressed) {
                        pressedIndex = checkPress(me.getPoint());
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (!animator.isRunning()) {
                        if (mousePressed && isOver(me.getPoint())) {
                            startAnimate();
                        } else {
                            int index = checkPress(me.getPoint());
                            if (index != -1) {
                                if (index == pressedIndex) {
                                    selectedIndex = index;
                                    runEvent();
                                    startAnimate();
                                }
                            }
                        }
                    }
                }
            }
        };
        addMouseMotionListener(mouseEvent);
        addMouseListener(mouseEvent);
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                borderColor = focusBorderColor;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                borderColor = normalBorderColor;
                repaint();
                if (option) {
                    startAnimate();
                }
            }
        });
        initAnimator();
    }

    public void addOption(SearchOption option) {
        items.add(option);
        if (selectedIndex == -1) {
            selectedIndex = 0;
            runEvent();
        }
    }

    public void addEventOptionSelected(SearchOptionEvent event) {
        events.add(event);
    }

    public SearchOption getSelectedOption() {
        if (selectedIndex == -1) {
            return null;
        } else {
            return items.get(selectedIndex);
        }
    }

    public boolean isSelected() {
        return selectedIndex >= 0;
    }

    public void setSelectedIndex(int index) {
        selectedIndex = index;
        runEvent();
        repaint();
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    private void runEvent() {
        for (SearchOptionEvent event : events) {
            event.optionSelected(getSelectedOption(), selectedIndex);
        }
    }

    private void startAnimate() {
        if (animator.isRunning()) {
            float f = animator.getTimingFraction();
            animator.stop();
            animator.setStartFraction(1f - f);
        } else {
            animator.setStartFraction(0f);
        }
        option = !option;
        animator.start();
    }

    private boolean isOver(Point mouse) {
        if (!option) {
            return shape.contains(mouse);
        }
        return false;
    }

    private int checkPress(Point mouse) {
        int index = -1;
        if (!items.isEmpty() && option) {
            double width = getWidth() / items.size();
            for (int i = 0; i < items.size(); i++) {
                if (new Rectangle2D.Double(width * i, 0, width, getHeight()).contains(mouse)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    private void initAnimator() {
        animator = new Animator(500, new TimingTargetAdapter() {
            @Override
            public void begin() {
                setEditable(!option);
            }

            @Override
            public void timingEvent(float fraction) {
                if (option) {
                    animate = fraction;
                } else {
                    animate = 1f - fraction;
                }
                repaint();
            }
        });
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        super.paintComponent(g);

        g2.setFont(font);
        g2.setColor(Color.GRAY);
        FontMetrics fm = g2.getFontMetrics();
        int iconY = (getHeight() + fm.getAscent()) / 2 - 2;
        g2.drawString("🔎", 10, iconY);

    g2.setStroke(new BasicStroke(3f));
    g2.setColor(borderColor);
    g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

        g2.dispose();

    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintHint(g2);
        double x = getWidth() - 35;
        double y = 2;
        x -= (x - 2) * animate;
        double height = getHeight() - 1;
        double round = height - height * animate;
        Area area = new Area(new RoundRectangle2D.Double(x-1, y-2, height, height, round, round));
        Path2D p = new Path2D.Double();
        p.moveTo(x + height / 2, y-2);
        p.lineTo(getWidth(), y-2);
        p.lineTo(getWidth() , y + height);
        p.lineTo(x + height / 2, y + height);
        area.add(new Area(p));
        g2.setPaint(new GradientPaint(new Point2D.Double(x, 0), colorOverlay1, new Point2D.Double(getWidth(), 0), colorOverlay2));
        g2.fill(area);
        shape = area;

        g2.setColor(borderColor);
        g2.draw(area);

        drawItem(g2, x, y, getWidth() - 2, height);
        g2.dispose();
    }

    private void paintHint(Graphics2D g2) {
        if (getText().length() == 0) {
            int h = getHeight();
            Insets ins = getInsets();
            FontMetrics fm = g2.getFontMetrics();
            int c0 = getBackground().getRGB();
            int c1 = getForeground().getRGB();
            int m = 0xfefefefe;
            int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
            g2.setColor(new Color(c2, true));
            g2.drawString(hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }

    private void drawItem(Graphics2D g2, double x, double y, double width, double height) {
        double w = width - x;
        double per = w / items.size();
        for (int i = 0; i < items.size(); i++) {
            drawIcon(g2, x + i * per, y, per, height, i);
        }
    }

    private void drawIcon(Graphics2D g2, double x, double y, double width, double height, int index) {
        Composite oldComposite = g2.getComposite();
        if (index != selectedIndex) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, animate));
        } else {
            width = (width <= 35 ? 35 : width);
            x = (x > getWidth() - 34 ? getWidth() - 34 : x);
        }
        ImageIcon image = toImage(index);
        double ix = x + ((width - image.getIconWidth()) / 2);
        double iy = y + ((height - image.getIconHeight()) / 2);
        g2.drawImage(image.getImage(), (int) ix, (int) iy, null);
        g2.setComposite(oldComposite);
    }

    private ImageIcon toImage(int index) {
        return (ImageIcon) items.get(index).getIcon();
    }
    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public Color getColorOverlay1() {
        return colorOverlay1;
    }

    public void setColorOverlay1(Color colorOverlay1) {
        this.colorOverlay1 = colorOverlay1;
    }

    public Color getColorOverlay2() {
        return colorOverlay2;
    }

    public void setColorOverlay2(Color colorOverlay2) {
        this.colorOverlay2 = colorOverlay2;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Top Panel UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 40));
        TextFieldSearchOption txt= new TextFieldSearchOption();
        txt.setPreferredSize(new Dimension(300, 40));
      txt.addEventOptionSelected((option, index) ->
          txt.setHint("Search by " + option.getName() + "..."));

        txt.addOption(new SearchOption("Name", new ImageIcon(TextFieldSearchOption.class.getResource("/icons/searchOptionIcons/user.png"))));
        txt.addOption(new SearchOption("Tel", new ImageIcon(TextFieldSearchOption.class.getResource("/icons/searchOptionIcons/tel.png"))));
        txt.addOption(new SearchOption("Email", new ImageIcon(TextFieldSearchOption.class.getResource("/icons/searchOptionIcons/email.png"))));
        txt.addOption(new SearchOption("Address", new ImageIcon(TextFieldSearchOption.class.getResource("/icons/searchOptionIcons/address.png"))));


        panel.add(txt);

        panel.add(new JButton("click me"));
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
