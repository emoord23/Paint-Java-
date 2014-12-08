package Codigo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import sun.awt.image.ImageAccessException;
import sun.java2d.pipe.DrawImage;

/*
        ###################################################################
	#                 *| PAINT SIN ÁNIMO DE LUCRO |*                  #
	#                           CREADO POR                            #
	#                       EMILIO MORENO ORDUÑA                      #
	#                           1º DE DAM                             #
	###################################################################
	
	SE HA MEJORADO EL PROGRAMA INCLUYENDO LAS SIGUIENTES FORMAS
                - TRAZADO LIBRE
                - GOMA
                - TRIANGULO
                - SPRAY (TIENE SU PROPIO SLIDER PARA LA DENSIDAD)
                - FORMAS RELLENAS
        SE HA ACTUALIZADO EL MÉTODO PARA PINTAR LAS FORMAS CUADRADO Y TIANGULO EN TODAS DIRECCIONES
        SE HA CORREGIDO LOS FALLOS DEL DESHACER, QUE SI DESHACÍAMOS, PINTABAMOS Y VOLVIAMOS A DESHACER
        SE CARGABAN BUFFERS ANTIGUOS
        SE HAN INTRODUCIDO MENUS Y EL BOTÓN PARA CREAR UN ARCHIVO NUEVO
	
* Este programa ejecuta una versión del cálica PAINT
* Permite dibujar direfentes formas, aplicar colores, línea discontinua, grosor, relleno, deshacer, 
* guardar archivos en .*png y crear un archivo nuevo

 */
public class VentanaPaint extends javax.swing.JFrame {
   
    //aquí guardamos en cual de los buffers estamos dibujando
    int cuentaBuffer = 0;
    //definimos el tamaño 
    int alto = 1024;
    int ancho = 1280;
    
    
    //Creamos el objeto linea.
    Line2D.Double linea = new Line2D.Double();

    //Creamos el objeto rectangulo
    Rectangle2D.Double rectangulo = new Rectangle2D.Double();

    //Creamos el objeto circulo
    Ellipse2D.Double circulo = new Ellipse2D.Double();

    //Creamos el objeto circulo  para el pincel
    Ellipse2D.Double pincel = new Ellipse2D.Double();

    //Creamos el objeto circulo  para la goma
    Ellipse2D.Double goma = new Ellipse2D.Double();

    //Creamos el objeto triangulo 
    Polygon triangulo = new Polygon();
    
    //Creamos el objeto spray
    Ellipse2D.Double spray = new Ellipse2D.Double();
    //Creamos el objeto random
    Random r = new Random();
    
    
    Image imagen = null;

    //Inicializamos los puntos que formaran el array de las coordenadas
    //de los 3 puntos del triángulo
    
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;
    int x3 = 0;
    int y3 = 0;
    //Inicializamos la variables que me serviran de punto de origen auxiliar 
    //al pintar el circulo y el rectángulo en cualquier sentido
    int evtx1 = 0;
    int evty1 = 0;
   
    //Selecciona el color inicial para todas las formas
    Color colorSeleccionado = Color.black;
    
    //Define el arrayList que solo guarda tipos <BufferedImage>
     ArrayList<BufferedImage> listaBuffers = new ArrayList();
   
     
    //Creamos el buffer
    private BufferedImage buffer = null;
    //con esta variable podemos asignar cada obejo a un boton etc..
    String forma = "";
    
    @Override
    //con esto estamos sobreescribiendo el método de debajo
    public void paint(Graphics g) {
        super.paintComponents(g);
        //apunta sobre el jpanel y le pinta el buffer, de esta manera hagamos lo que hagamos
        //con la ventana siempre se actualizara pintando lo útlimo.
        Graphics2D g2 = (Graphics2D) jPanel1.getGraphics();
        g2.drawImage(buffer, 0, 0, null);

    }

         /*
        Este es el constructor, método que no devuelve nada y se llama igual que la clase
        */
    public VentanaPaint() { 
        initComponents();
        jDialog1.setSize(ancho, alto);
        jLabelGrosor.setText(String.valueOf(jSliderGrosor.getValue()));
        jLabelDensidad.setText(String.valueOf(jSliderDensidad.getValue()));
       
        
        //Le otorgamos al buffer las medidas del jPanel
        int ancho = jPanel1.getWidth();
        int alto = jPanel1.getHeight()
                ;
        //Definiimos el buffer y lo ligamos al jPanel le damos color blanco y el tamaño del jpanel
        buffer = (BufferedImage) jPanel1.createImage(ancho, alto);
        Graphics2D g2 = buffer.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, ancho, alto);
        jButton1.setBackground(colorSeleccionado);//Colorea el boton de cambio de color
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jColorChooser1 = new javax.swing.JColorChooser();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jDialog2 = new javax.swing.JDialog();
        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jButtonDensidad = new javax.swing.JButton();
        jSliderGrosor = new javax.swing.JSlider();
        jLabelGrosor = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jSliderDensidad = new javax.swing.JSlider();
        jLabelDensidad = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButtonPincel = new javax.swing.JButton();
        jButtonRectangulo = new javax.swing.JButton();
        jButtonColor = new javax.swing.JButton();
        jButtonDeshacer = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButtonCirculo = new javax.swing.JButton();
        jButtonLinea = new javax.swing.JButton();
        jButtonSpray = new javax.swing.JButton();
        jCheckBoxRelleno = new javax.swing.JCheckBox();
        jButtonTriangulo = new javax.swing.JButton();
        jButtonGoma = new javax.swing.JButton();
        jCheckBoxDiscontinuo = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItemDeshacer = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemCirculo = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        jDialog1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jDialog1.setMaximumSize(new java.awt.Dimension(615, 400));
        jDialog1.setPreferredSize(new java.awt.Dimension(615, 400));
        jDialog1.setResizable(false);

        jColorChooser1.setMaximumSize(new java.awt.Dimension(603, 316));

        jButton8.setText("Aceptar");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton8MousePressed(evt);
            }
        });

        jButton9.setText("Cancelar");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton9MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jColorChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(212, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addComponent(jColorChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog2Layout.createSequentialGroup()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(1280, 800));
        setPreferredSize(new java.awt.Dimension(1280, 800));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setPreferredSize(new java.awt.Dimension(1125, 700));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel1MouseReleased(evt);
            }
        });
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1121, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 686, Short.MAX_VALUE)
        );

        jButtonDensidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/densidad.png"))); // NOI18N
        jButtonDensidad.setEnabled(false);
        jButtonDensidad.setPreferredSize(new java.awt.Dimension(150, 50));
        jButtonDensidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDensidadActionPerformed(evt);
            }
        });

        jSliderGrosor.setMaximum(70);
        jSliderGrosor.setValue(5);
        jSliderGrosor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSliderGrosor.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jSliderGrosorMouseDragged(evt);
            }
        });

        jLabelGrosor.setText("jLabel1");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/grosor.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(150, 50));

        jSliderDensidad.setMaximum(500);
        jSliderDensidad.setMinimum(20);
        jSliderDensidad.setValue(30);
        jSliderDensidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSliderDensidad.setEnabled(false);
        jSliderDensidad.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jSliderDensidadMouseDragged(evt);
            }
        });

        jLabelDensidad.setText("jLabel2");
        jLabelDensidad.setEnabled(false);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GUARDARm.png"))); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GUARDAR.png"))); // NOI18N
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton3MousePressed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/NUEVOm.png"))); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/NUEVO.png"))); // NOI18N
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton4MousePressed(evt);
            }
        });

        jButtonPincel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/brocham.png"))); // NOI18N
        jButtonPincel.setPreferredSize(new java.awt.Dimension(50, 50));
        jButtonPincel.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/brocha.png"))); // NOI18N
        jButtonPincel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonPincelMousePressed(evt);
            }
        });

        jButtonRectangulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rectangulom.png"))); // NOI18N
        jButtonRectangulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonRectangulo.setPreferredSize(new java.awt.Dimension(50, 50));
        jButtonRectangulo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rectangulo.png"))); // NOI18N
        jButtonRectangulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonRectanguloMousePressed(evt);
            }
        });

        jButtonColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/colorm.png"))); // NOI18N
        jButtonColor.setPreferredSize(new java.awt.Dimension(50, 50));
        jButtonColor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/color.png"))); // NOI18N
        jButtonColor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonColorMousePressed(evt);
            }
        });

        jButtonDeshacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/undom.png"))); // NOI18N
        jButtonDeshacer.setPreferredSize(new java.awt.Dimension(50, 50));
        jButtonDeshacer.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/undo.png"))); // NOI18N
        jButtonDeshacer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonDeshacerMousePressed(evt);
            }
        });

        jButton1.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonCirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/circulom.png"))); // NOI18N
        jButtonCirculo.setPreferredSize(new java.awt.Dimension(50, 50));
        jButtonCirculo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/circulo.png"))); // NOI18N
        jButtonCirculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonCirculoMousePressed(evt);
            }
        });

        jButtonLinea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lineam.png"))); // NOI18N
        jButtonLinea.setPreferredSize(new java.awt.Dimension(50, 50));
        jButtonLinea.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/linea.png"))); // NOI18N
        jButtonLinea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonLineaMousePressed(evt);
            }
        });

        jButtonSpray.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/spraym.png"))); // NOI18N
        jButtonSpray.setPreferredSize(new java.awt.Dimension(50, 50));
        jButtonSpray.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/spray.png"))); // NOI18N
        jButtonSpray.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonSprayMousePressed(evt);
            }
        });

        jCheckBoxRelleno.setText("Relleno");

        jButtonTriangulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/triangulom.png"))); // NOI18N
        jButtonTriangulo.setPreferredSize(new java.awt.Dimension(50, 50));
        jButtonTriangulo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/triangulo.png"))); // NOI18N
        jButtonTriangulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonTrianguloMousePressed(evt);
            }
        });

        jButtonGoma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/gomam.png"))); // NOI18N
        jButtonGoma.setPreferredSize(new java.awt.Dimension(50, 50));
        jButtonGoma.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/goma.png"))); // NOI18N
        jButtonGoma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonGomaMousePressed(evt);
            }
        });

        jCheckBoxDiscontinuo.setText("Discontinuo");

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jMenu1.setText("Archivo");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/NUEVOm.png"))); // NOI18N
        jMenuItem2.setText("Nuevo");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem2MousePressed(evt);
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GUARDARm.png"))); // NOI18N
        jMenuItem1.setText("Guardar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("Editar");

        jMenuItemDeshacer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemDeshacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/undom.png"))); // NOI18N
        jMenuItemDeshacer.setText("Deshacer");
        jMenuItemDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeshacerActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItemDeshacer);

        jMenuBar1.add(jMenu4);

        jMenu2.setText("Formas");

        jMenuItemCirculo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/circulom.png"))); // NOI18N
        jMenuItemCirculo.setText("Circulo");
        jMenuItemCirculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCirculoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemCirculo);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/gomam.png"))); // NOI18N
        jMenuItem9.setText("Goma");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/brocham.png"))); // NOI18N
        jMenuItem10.setText("Pincel");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lineam.png"))); // NOI18N
        jMenuItem3.setText("Linea");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rectangulom.png"))); // NOI18N
        jMenuItem4.setText("Rectangulo");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/spraym.png"))); // NOI18N
        jMenuItem8.setText("Spray");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/triangulom.png"))); // NOI18N
        jMenuItem5.setText("Triangulo");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButtonLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonCirculo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButtonDeshacer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonPincel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonSpray, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonRectangulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonTriangulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonGoma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jCheckBoxDiscontinuo)
                            .addComponent(jCheckBoxRelleno)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSliderGrosor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelGrosor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                        .addComponent(jButtonDensidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSliderDensidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelDensidad)
                        .addGap(102, 102, 102))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonDensidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelGrosor)
                                    .addComponent(jSliderGrosor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelDensidad)
                                .addComponent(jSliderDensidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonGoma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonDeshacer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonSpray, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonPincel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRectangulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonCirculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonTriangulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addComponent(jCheckBoxRelleno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxDiscontinuo))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        /*
        EVENTO PRESSED DEL MOUSE 
        */
        Graphics2D g2;
        cuentaBuffer++;//aumentamos la variable que nos identifica cada buffer 
        BufferedImage bufferAuxiliar = (BufferedImage) jPanel1.createImage(ancho, alto);//se crea un buffer auxiliar para poder 
        //grabar el buffer en una linea de memoria diferente. Al pintarse cada vez se pierde si no se guarda en el arraylist
        //Este buffer auxiliar hace como cuando teníamos un sólo buffer y lo pintabamos encima, pero como queremos guardar una 
        //copia hay que crear un buffer con cada click
        g2 = (Graphics2D) bufferAuxiliar.getGraphics();
        g2.drawImage(buffer, 0, 0, null);
        //Añadimos el buffer al arraylist, se añade el auxiliar, porque hemos copiado el buffer al auxiliar
        listaBuffers.add(bufferAuxiliar);
        


        //Definimos los dos puntos que crean una linea. evt es el evento del mouse pressed  que guardara las coodenadas
        switch (forma) {
            ////////////////LINEA/////////////////////
            case "linea": {
                //se definen el punto inicial de la línea
                    linea.x1 = evt.getX();
                    linea.y1 = evt.getY();
                   
            }
            break;
            ////////////////RECTANGULO/////////////////////
            case "rectangulo": {
                rectangulo.x = evt.getX();//coordenadas xy de punto de inicio
                rectangulo.y = evt.getY();
                evtx1 = evt.getX();//coordenadas xy secundarias para poder dibujar en todos los cuadrantes
                evty1 = evt.getY();
                rectangulo.width = evtx1;
                rectangulo.height = evty1;
            }
            break;
            ////////////////PINCEL/////////////////////
            case "pincel": {
                pincel.x = evt.getX() - pincel.width / 2; //cordenada x del centro del circulo
                pincel.y = evt.getY() - pincel.height / 2; // coordenada y del centro del circulo
                pincel.width = jSliderGrosor.getValue();//seleciona el grosor con el slider
                pincel.height = pincel.width;//es igual porque es un círculo
            }
            break;
            ////////////////TRIANGULO/////////////////////
            case "triangulo": {
                //dejo fjo el primer vértice del triangulo
                x1 = evt.getX(); 
                y1 = evt.getY();   
            }
            break;
            ////////////////CIRCULO/////////////////////
            case "circulo": {
                circulo.x = evt.getX();//coordenadas xy de punto de inicio
                circulo.y = evt.getY();
                evtx1 = evt.getX();//coordenadas xy secundarias para poder dibujar en todos los cuadrantes
                evty1 = evt.getY();
                circulo.width = evtx1;
                circulo.height = evty1;
            }
            break;
            ////////////////GOMA/////////////////////
            case "goma": {
                goma.x = evt.getX() - goma.width / 2; //cordenada x del centro del circulo
                goma.y = evt.getY() - goma.height / 2; // coordenada y del centro del circulo
                goma.width = jSliderGrosor.getValue();//seleciona el grosor con el slider
                goma.height = goma.width;//es igual porque es un círculo

            }
           
            break;
            ////////////////SPRAY/////////////////////
            case "spray": {
                
                spray.x = evt.getX(); //cordenada x de inicio
                spray.y = evt.getY(); // coordenada y de inicio del spray
                spray.width = jSliderGrosor.getValue();//seleccionamos el grosor con el slider
                spray.height = spray.width; //es igual porque son círculos
            }

            break;
        }
    }//GEN-LAST:event_jPanel1MousePressed

    @SuppressWarnings("empty-statement")
    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        /*
        EVENTO DRAGGED DEL MOUSE 
        */
        //Apuntamos al jpanell
        Graphics2D g2 = (Graphics2D) jPanel1.getGraphics();
        //le pintamos el buffer encima al jpanel para que no se vean todas la lineas que formariamos al mover el raton

        g2.drawImage(buffer, 0, 0, null); //0,0 es la cordenada xy de la esquina superior izquierda donde empieza a pintarse
        g2.setColor(colorSeleccionado);
        if (jCheckBoxDiscontinuo.isSelected()) { // esto pinta discontunuio si esta el checkbox seleccionado o no
           pintaEnDiscontinuo(g2);
        } else {//si no esta seleccionado pinta continuo
            //seleccionamos el grosor antes de pintar
            g2.setStroke(new BasicStroke(jSliderGrosor.getValue()));
        }
        switch (forma) {
            ////////////////LINEA/////////////////////
            case "linea": {
                    //Pintamos sobre el buffer la linea, actulizando la posición 
                    //de las segundas coordenadas e funcion de la posición del raton
                    linea.x2 = evt.getX();
                    linea.y2 = evt.getY();
                    g2.draw(linea);//Lo pinta relleno si la casilla relleno se selecciona
            }
            break;
            ////////////////RECTANGULO/////////////////////
            case "rectangulo": {
                pintaRectanguloEnCuadrantes(evt);//Método que permite pintarlo en todas direcciones y sentidos     
                if (jCheckBoxRelleno.isSelected()) {//Lo pinta relleno si la casilla relleno se selecciona
                   g2.fill(rectangulo);
               };
                g2.draw(rectangulo);

            }

            break;
            ////////////////PINCEL/////////////////////
            case "pincel": {
                //Le decimos al buffer que guarde todo lo que se vaya pintando 
                g2 = (Graphics2D) buffer.getGraphics();
                pincel.x = evt.getX() - pincel.width / 2; //cordenada x del centro del circulo
                pincel.y = evt.getY() - pincel.height / 2;  // coordenada y del centro del circulo
                g2.setColor(colorSeleccionado);
                g2.fill(pincel);
                g2.draw(pincel); //pinta todo el rato que tengamos el boton del raton presionado y moviendolo.

            }
            break;
            
            ////////////////TRIANGULO/////////////////////
            case "triangulo": {
                //el sengundo vertice lo hago móvil en funcion del raton
                x2 = evt.getX();
                y2 = evt.getY();
                //el tercer vértice se posiona en funcion de las distancias con y1 y la posicion del raton
                //a través de la fórmula del teorema de la altura h^2=m*n donde he simplificado que m = n
                x3 = evt.getX() - (2 * ((evt.getY() - y1) ^ 2));
                y3 = evt.getY();

                //Creamos el array de puntos con los tres vértices del triángulo;
                triangulo.npoints = 3;

                triangulo.xpoints[0] = x1;//generamso el array de puntos x
                triangulo.xpoints[1] = x2;
                triangulo.xpoints[2] = x3;

                triangulo.ypoints[0] = y1;//Generamos el  array de puntos Y
                triangulo.ypoints[1] = y2;
                triangulo.ypoints[2] = y3;
                if (jCheckBoxRelleno.isSelected()) {
                   g2.fill(triangulo);
               }
                g2.drawPolygon(triangulo.xpoints, triangulo.ypoints, triangulo.npoints);

            }
            break;
             ////////////////CIRCULO/////////////////////
            case "circulo": {
                pintaCirculoEnCuadrantes(evt);//Método que permite pintarlo en todas direcciones y sentidos
                if (jCheckBoxRelleno.isSelected()) {//Lo pinta relleno si la casilla relleno se selecciona
                   g2.fill(circulo);
               }
                g2.draw(circulo);
            }

            break;
            ////////////////GOMA/////////////////////
            case "goma": {
                //Le decimos al buffer que guarde todo lo que se vaya pintando 
                g2 = (Graphics2D) buffer.getGraphics();
                goma.x = evt.getX() - goma.width / 2; //cordenada x del centro del circulo
                goma.y = evt.getY() - goma.height / 2;  // coordenada y del centro del circulo
                g2.setColor(Color.WHITE);
                g2.fill(goma);
                g2.draw(goma);//pinta todo el rato que tengamos el boton del raton presionado y moviendolo.
            }
            break;
            ////////////////SPRAY/////////////////////
            case "spray": {
                //Le decimos al buffer que guarde todo lo que se vaya pintando 
                g2 = (Graphics2D) buffer.getGraphics(); 
                //Para generar el spray creo un bucle for de rango igual al valor del slider. El valor del es slider
                //es el número de puntos a pintar y por lo tanto la densidad del spray
                for (int i = 0; i < jSliderDensidad.getValue(); i++) {
                    
                    spray.x = evt.getX()  + r.nextInt(jSliderGrosor.getValue());; //Genero posiciones x aleatorias para los puntos y 
                    //limito la distancia al puntero con el valor del grosor que nos da el sliderGrosor 
                    spray.y = evt.getY()  + r.nextInt(jSliderGrosor.getValue());;  //Genero posiciones y aleatorias para los puntos y 
                    //limito la distancia al puntero con el valor del grosor que nos da el sliderGrosor
                    spray.width = r.nextInt(3);//tamaño de los puntos del spray, varian entre 1 y 3 px para hacerlo más realista
                    spray.height = spray.width;
                    
                    g2.setColor(colorSeleccionado);
                    g2.fill(spray);
                    g2.draw(spray);//pinta todo el rato que tengamos el boton del raton presionado y moviendolo.
                }
                
            }
            break;

        }


    }//GEN-LAST:event_jPanel1MouseDragged

    
    private void jPanel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseReleased
       
        /*
        EVENTO RELEASED DEL MOUSE 
        */
        Graphics2D g2;
        //Apuntamos al buffer y pintamos en él
        g2 = (Graphics2D) buffer.getGraphics();
        g2.setColor(colorSeleccionado);
        if (jCheckBoxDiscontinuo.isSelected()) { // esto espara siesta el checkbox seleccionado o no
            pintaEnDiscontinuo(g2);              //ejecute el método que genera líneas o contornos discontinuos
        } else {//si no esta seleccionado pinta continuo
            //seleccionamos el grosor antes de pintar
            g2.setStroke(new BasicStroke(jSliderGrosor.getValue()));
        }

        switch (forma) {
            ////////////////LINEA/////////////////////
            case "linea": {
                    // Selecionamos el punto final del la linea y 
                    //pintamos al soltar el boton del ratón
                    linea.x2 = evt.getX();
                    linea.y2 = evt.getY();
                    g2.draw(linea);
            }
            break;
            ////////////////RECTANGULO/////////////////////
            case "rectangulo": {
                
                pintaRectanguloEnCuadrantes(evt);//Método que permite pintarlo en todas direcciones y sentidos                
                if (jCheckBoxRelleno.isSelected()) {//Lo pinta relleno si la casilla relleno se selecciona
                   g2.fill(rectangulo);
               }
                g2.draw(rectangulo);
            }

            break;
            ////////////////PINCEL/////////////////////
            case "pincel": {
                g2.fill(pincel);
                
            }
            break;
            ////////////////TRIANGULO/////////////////////
            case "triangulo": {
                //el sengundo vertice lo hago móvil en funcion del raton
                x2 = evt.getX();
                y2 = evt.getY();
                //el tercer vértice se posiona en funcion de las distancias con y1 y la posicion del raton
                //a través de la fórmula del teorema de la altura h^2=m*n donde he simplificado que m = n
                x3 = evt.getX() - (2 * ((evt.getY() - y1) ^ 2));
                y3 = evt.getY();

                //Creamos el array de puntos con los tres vértices del triángulo;
                triangulo.npoints = 3;

                triangulo.xpoints[0] = x1;//generamso el array de puntos x
                triangulo.xpoints[1] = x2;
                triangulo.xpoints[2] = x3;

                triangulo.ypoints[0] = y1;//Generamos el  array de puntos Y
                triangulo.ypoints[1] = y2;
                triangulo.ypoints[2] = y3;

                if (jCheckBoxRelleno.isSelected()) {
                   g2.fill(triangulo);
               }
                g2.drawPolygon(triangulo.xpoints, triangulo.ypoints, triangulo.npoints);

            }
            break;
                ////////////////CIRCULO/////////////////////
            case "circulo": {
                pintaCirculoEnCuadrantes(evt);//Método que permite pintarlo en todas direcciones y sentidos
               if (jCheckBoxRelleno.isSelected()) {//Lo pinta relleno si la casilla relleno se selecciona
                   g2.fill(circulo);
               }
                g2.draw(circulo);

            }
            break;
                ////////////////GOMA/////////////////////
            case "goma": {
                //seleccionamos color blanco, relleno y pintamos para que al hacer sólo click tambíen pinte
                g2.setColor(Color.WHITE);
                g2.fill(goma);
                

            }
            break;
            ////////////////SPRAY/////////////////////
            case "spray": {
                g2 = (Graphics2D) buffer.getGraphics();
                //Para generar el spray creo un bucle for de rango igual al valor del slider. El valor del es slider
                //es el número de puntos a pintar y por lo tanto la densidad del spray
                for (int i = 0; i < jSliderDensidad.getValue(); i++) {
                    
                    spray.x = evt.getX()  + r.nextInt(jSliderGrosor.getValue());//Genero posiciones x aleatorias para los puntos y 
                    //limito la distancia al puntero con el valor del grosor que nos da el sliderGrosor 
                    spray.y = evt.getY()  + r.nextInt(jSliderGrosor.getValue());//Genero posiciones y aleatorias para los puntos y 
                    //limito la distancia al puntero con el valor del grosor que nos da el sliderGrosor
                    spray.width = r.nextInt(3);//tamaño de los puntos del spray, varian entre 1 y 3 px para hacerlo más realista
                    spray.height = spray.width;
                    g2.setColor(colorSeleccionado);
                    g2.fill(spray);
                    g2.draw(spray);//pinta al soltar el bonton del ratón para que pinte también haciendo click sólo
                }        
            }
            break;
        }
        //Apuntamos al jpanel y copiamos lo del buffer en él
        g2 = (Graphics2D) jPanel1.getGraphics();
        g2.drawImage(buffer, 0, 0, null);
    }//GEN-LAST:event_jPanel1MouseReleased
 
    private void pintaEnDiscontinuo(Graphics2D g2){
            
        //////////LIENAS DISCONTINUAS//////////////////
        
        //Este método genera una linea discontinua con el grosor del slider1
        //Es alicable a todos los objetos Graphics2D
        float guion[] = {10.0f};//definimos la lineas discontinuas 10.0f define el tamaño en float 
        g2.setStroke(new BasicStroke(
                                     jSliderGrosor.getValue(),
                                     BasicStroke.CAP_BUTT,
                                     BasicStroke.JOIN_MITER,
                                     10.0f,
                                     guion,
                                     0.0f));
    }
    private void deshacer (Graphics2D g2){
        
        //////////////////////DESHACER//////////////////
        
        //si delimitamos a >0 evitamos el error de intentar
        //posiconarnos en un valar negativo del arraylist
        if (cuentaBuffer>0){
            g2 = (Graphics2D) buffer.getGraphics();
            BufferedImage bufferAuxiliar = listaBuffers.get(cuentaBuffer - 1); //coge el buffer último de la lista
            cuentaBuffer--; //como quitamos un buffer tenemos que restarle uno al contador
            listaBuffers.remove(cuentaBuffer);//Borramos el buffer que tenga la poscion cuentabuffer de la lista
            //porque si no, cuando despues de deshacer algo volvamos a pintar y querramos deshacer, las posicones de los nuevos buffers
            //no se corresponderan con el numero que tenga el cuenta buffer
            g2.drawImage(bufferAuxiliar, 0, 0, null);
            repaint();
        }
    }
    
    private void pintaRectanguloEnCuadrantes( java.awt.event.MouseEvent evt){
         
        ///////////PINTA EL OBJETO RECTANGULO EN TODAS DIRECCIONES///////////
         
         //para poder crear el objento en las direcciones negativas del eje
         //hay que dejar fijos el width y el heith en funcion de la distancia 
         //al puntero del raton como si fueran puntos fijos. Y el punto de inicio
         //permence movil difiendolo en el mouseDragged
         
         ///MOVIMIENTO EN X POSITIVO(DERECHA)///
         if ((evt.getX() >= rectangulo.x)) { rectangulo.width = evt.getX() - rectangulo.x; }
         
         ///MOVIMIENTO EN Y POSITIVO(ABAJO)///
         if ((evt.getY() >= rectangulo.y)) { rectangulo.height = evt.getY() - rectangulo.y; }
                
         ///MOVIMIENTO EN X NEGATIVO(IZQUEIRDA)///
         if (evt.getX() <= evtx1) { rectangulo.x = evt.getX();//Esto permite que el punto de origen se mueva con el ratón
                                    rectangulo.width = evtx1 - rectangulo.getX();//Esto mantiene el ancho como un punto fijo 
                                                                          //al variar en funcion de la posición del raton
                                  }
         ///MOVIMIENTO EN Y NEGATIVO(ARRIBA)///
         if (evt.getY() <= evty1) { rectangulo.y = evt.getY();//Esto permite que el punto de origen se mueva con el ratón
                                    rectangulo.height = evty1 - rectangulo.getY();//Esto mantiene el alto como un punto fijo 
                                                                                   //al variar en funcion de la posición del raton
                                  }
     }
    private void pintaCirculoEnCuadrantes( java.awt.event.MouseEvent evt){
        ///////////PINTA EL OBJETO CIRCULO EN TODAS DIRECCIONES///////////
         
         //para poder crear el objeto en los sentidos negativos del eje
         //hay que dejar fijos el width y el heith en funcion de la distancia 
         //al puntero del raton, como si fueran puntos fijos. Y el punto de inicio
         //permence móvil difiendolo en el mouseDragged
      
        ///MOVIMIENTO EN X POSITIVO(DERECHA)///
        if ((evt.getX() >= circulo.x)) { circulo.width = evt.getX() - circulo.x; }
        
        ///MOVIMIENTO EN Y POSITIVO(ABAJO)///
        if ((evt.getY() >= circulo.y)) { circulo.height = evt.getY() - circulo.y; }
        
        ///MOVIMIENTO EN X NEGATIVO(IZQUEIRDA)///
        if (evt.getX() <= evtx1) { circulo.x = evt.getX();//Esto permite que el punto de origen se mueva con el ratón
                                   circulo.width = evtx1 - circulo.getX();//Esto mantiene el ancho como un punto fijo 
                                                                          //al variar en funcion de la posición del raton
                                 }
        ///MOVIMIENTO EN Y NEGATIVO(ARRIBA)///
                if (evt.getY() <= evty1) { circulo.y = evt.getY();//Esto permite que el punto de origen se mueva con el ratón
                                           circulo.height = evty1 - circulo.getY();//Esto mantiene el alto como un punto fijo 
                                                                                   //al variar en funcion de la posición del raton
                                         }            
    }
    
    private void guardar(){
        ///////////////////////GUARDAR ARCHIVOS EN PNG////////////////////////// 
        
         //Se le aplica el item del menu (guardar)
        FileNameExtensionFilter filtro =
                new FileNameExtensionFilter("JPG,PNG,GIF,", "jpg", "png", "gif");
        //muestra los strings con las extensiones que definamos en fileNameExtensionFilter
        jFileChooser1.setFileFilter(filtro);
        //muestra la pantalla predefinida en Netbeans de guardar archivos
        int seleccion = jFileChooser1.showSaveDialog(this);
        
        //Si el boton seleccionado tiene el valor = aprove (aceptar) crea un fichero
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = new File(jFileChooser1.getSelectedFile() + ".jpg");

            try {
                ImageIO.write(buffer, "jpg", fichero);
            } catch (IOException ex) {
                Logger.getLogger(VentanaPaint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Si el boton seleccionado tiene el valor = cancel (cancela)
        if (seleccion == JFileChooser.CANCEL_OPTION) {}
        // Si el boton seleccionado tiene el valor = error (aceptar) mostramos un mensaje
        if (seleccion == JFileChooser.ERROR_OPTION) {}
    }
    
    private void nuevo(){
        ///////////ARCHIVO NUEVO///////////////
        
        //primero definimos el buffer de nuevo y lo pintamos sobre el jpanel
        Graphics2D g2 = buffer.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, ancho, alto);
        g2 = (Graphics2D) jPanel1.getGraphics();
        g2.drawImage(buffer, 0, 0, null);
        //luego hay que limpiar el array list para que al utilizar el Deshacer 
        //no aparezcan buffers antiguos guardados. Y tambien resetear el contador para que las
        //nuevas posiciones se correspondan con las del arraylist 
        if (cuentaBuffer>0){
        cuentaBuffer=0;
        listaBuffers.clear();
        }
        
    }
    private void jButton8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MousePressed
        //////////BOTON ACEPTAR DEL SELECTOR DE COLOR/////////////////
        colorSeleccionado = jColorChooser1.getColor(); //Selecciona el color del chooser
        jButton1.setBackground(colorSeleccionado);//Colorea el boton de cambio de color
        jDialog1.setVisible(false);// cierra la ventana dialog al dar a aceptar
    }//GEN-LAST:event_jButton8MousePressed

    private void jButton9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MousePressed
        ////////////////BOTON CANCELAR DEL SELECTOR DE COLOR///////////
        jDialog1.setVisible(false);// cierra la ventana al dar a cancelar
    }//GEN-LAST:event_jButton9MousePressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    ///////////////////////GUARDAR ARCHIVOS EN PNG////////////////////////// 
        guardar();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
     ///////////ARCHIVO NUEVO///////////////
    nuevo();       
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2MousePressed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        //////////////////Menú Rectangulo//////////////////////////
        forma = "rectangulo";
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItemCirculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCirculoActionPerformed
        //////////////////Menú Circulo//////////////////////////
        forma = "circulo";
    }//GEN-LAST:event_jMenuItemCirculoActionPerformed

    private void jSliderGrosorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSliderGrosorMouseDragged
       //////////////////SLIDER PARA DAR GROSOR//////////////////
        jLabelGrosor.setText(String.valueOf(jSliderGrosor.getValue()));
        // esto nos indica en
        //un label el tamaño del slider y al colocarlo en el mouse realeased se
        //acutializa el valor al moverlo
    }//GEN-LAST:event_jSliderGrosorMouseDragged

    private void jButtonSprayMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSprayMousePressed
        ////////////////Botón Círculo/////////////////
        forma = "spray";
        //Esto desactiva los elementos vinculados al Spray
        jSliderDensidad.setEnabled(true);
        jLabelDensidad.setEnabled(true);
        jButtonDensidad.setEnabled(true);

    }//GEN-LAST:event_jButtonSprayMousePressed

    private void jButtonCirculoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCirculoMousePressed
        ////////////////Botón Círculo/////////////////
        forma = "circulo";
        //Esto desactiva los elementos vinculados al Spray
        jSliderDensidad.setEnabled(false);
        jLabelDensidad.setEnabled(false);
        jButtonDensidad.setEnabled(false);
    }//GEN-LAST:event_jButtonCirculoMousePressed

    private void jButtonPincelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonPincelMousePressed
        ////////////////Botón Pincel/////////////////
        forma = "pincel";
        //Esto desactiva los elementos vinculados al Spray
        jSliderDensidad.setEnabled(false);
        jLabelDensidad.setEnabled(false);
        jButtonDensidad.setEnabled(false);
    }//GEN-LAST:event_jButtonPincelMousePressed

    private void jButtonTrianguloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTrianguloMousePressed
        ////////////////Botón Triángulo/////////////////
        forma = "triangulo";
        //Esto desactiva los elementos vinculados al Spray
        jSliderDensidad.setEnabled(false);
        jLabelDensidad.setEnabled(false);
        jButtonDensidad.setEnabled(false);
    }//GEN-LAST:event_jButtonTrianguloMousePressed

    private void jButtonGomaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonGomaMousePressed
        ////////////////Botón Goma/////////////////
        forma = "goma";
        //Esto desactiva los elementos vinculados al Spray
        jSliderDensidad.setEnabled(false);
        jLabelDensidad.setEnabled(false);
        jButtonDensidad.setEnabled(false);
    }//GEN-LAST:event_jButtonGomaMousePressed

    private void jButtonRectanguloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRectanguloMousePressed
        ////////////////Botón Rectángulo/////////////////
        forma = "rectangulo";
        //Esto desactiva los elementos vinculados al Spray
        jSliderDensidad.setEnabled(false);
        jLabelDensidad.setEnabled(false);
        jButtonDensidad.setEnabled(false);
    }//GEN-LAST:event_jButtonRectanguloMousePressed

    private void jButtonLineaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonLineaMousePressed
         ////////////////Botón Linea/////////////////
        forma = "linea";
        //Esto desactiva los elementos vinculados al Spray
        jSliderDensidad.setEnabled(false);
        jLabelDensidad.setEnabled(false);
        jButtonDensidad.setEnabled(false);
    }//GEN-LAST:event_jButtonLineaMousePressed

    private void jButtonColorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonColorMousePressed
        ////////////////BOTON SELECCIÓN DE COLOR/////////////////
        jDialog1.setVisible(true);//abre el jdialog del selector de color
    }//GEN-LAST:event_jButtonColorMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonDeshacerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDeshacerMousePressed
        ////////////////Boton Deshacer/////////////////
        deshacer(null);
    }//GEN-LAST:event_jButtonDeshacerMousePressed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
         ////////////////Menú Pincel/////////////////
        forma = "pincel";
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItemDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeshacerActionPerformed
         ////////////////Menú Deshacer/////////////////
        deshacer(null);
    }//GEN-LAST:event_jMenuItemDeshacerActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
         ////////////////Menú Goma/////////////////
        forma = "goma";
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
         ////////////////Menú Linea/////////////////
        forma = "linea";
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
         ////////////////Menú Spray/////////////////
        forma = "spray";
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
         ////////////////Menú Triángulo/////////////////
        forma = "triangulo";
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButtonDensidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDensidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDensidadActionPerformed

    private void jSliderDensidadMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSliderDensidadMouseDragged
        //////////////////SLIDER PARA DAR DENSIDAD AL SPRAY///////////////
        jLabelDensidad.setText(String.valueOf(jSliderDensidad.getValue()));
        // esto nos indica en
        //un label el tamaño del slider y al colocarlo en el mouse dragged se
        //acutializa el valor al moverlo
    }//GEN-LAST:event_jSliderDensidadMouseDragged

    private void jButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MousePressed
        /////////////////BOTÓN NUEVO///////////////////
        nuevo();
    }//GEN-LAST:event_jButton4MousePressed

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
         ///////////////////////GUARDAR ARCHIVOS EN PNG////////////////////////// 
        guardar();
    }//GEN-LAST:event_jButton3MousePressed
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPaint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPaint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPaint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPaint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPaint().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonCirculo;
    private javax.swing.JButton jButtonColor;
    private javax.swing.JButton jButtonDensidad;
    private javax.swing.JButton jButtonDeshacer;
    private javax.swing.JButton jButtonGoma;
    private javax.swing.JButton jButtonLinea;
    private javax.swing.JButton jButtonPincel;
    private javax.swing.JButton jButtonRectangulo;
    private javax.swing.JButton jButtonSpray;
    private javax.swing.JButton jButtonTriangulo;
    private javax.swing.JCheckBox jCheckBoxDiscontinuo;
    private javax.swing.JCheckBox jCheckBoxRelleno;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabelDensidad;
    private javax.swing.JLabel jLabelGrosor;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItemCirculo;
    private javax.swing.JMenuItem jMenuItemDeshacer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSlider jSliderDensidad;
    private javax.swing.JSlider jSliderGrosor;
    // End of variables declaration//GEN-END:variables
}
