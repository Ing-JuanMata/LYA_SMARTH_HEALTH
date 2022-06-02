package codigo;

import compilerTools.Functions;
import compilerTools.TextColor;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.undo.UndoManager;
import say.swing.JFontChooser;

public class FrmPrincipal extends javax.swing.JFrame {

    private String archivo = "";
    private NumeroLinea NumLinea;
    DefaultTableModel modeloEstatico;
    DefaultTableModel modeloDinamico;
    public static ArrayList<String> errores = new ArrayList<>();
    private ArrayList<Token> tokens = new ArrayList<>();
    private ArrayList<TextColor> colores = new ArrayList<>();

    private final UndoManager administradorCambios;

    public FrmPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("SmartHealth");
        NumLinea = new NumeroLinea(txtEntrada);
        scrollEntrada.setRowHeaderView(NumLinea);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/Atlas.png")).getImage());
        modeloEstatico = (DefaultTableModel) tablaEstatica.getModel();
        modeloDinamico = (DefaultTableModel) tablaDinamica.getModel();

        tablaEstatica.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tablaDinamica.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        // Oculta/Mustra las tablas a pantalla
        validarTablas();

        // Administrador de cambios en sintaxis
        administradorCambios = new UndoManager();                //construye una instancia de UndoManager

        txtEntrada.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                administradorCambios.addEdit(e.getEdit());
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator3 = new javax.swing.JSeparator();
        barraHerramientas = new javax.swing.JToolBar();
        sep0 = new javax.swing.JToolBar.Separator();
        btnAbrir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        sep1 = new javax.swing.JToolBar.Separator();
        btnCortar = new javax.swing.JButton();
        btnCopiar = new javax.swing.JButton();
        btnPegar = new javax.swing.JButton();
        sep2 = new javax.swing.JToolBar.Separator();
        btnDeshacer = new javax.swing.JButton();
        btnRehacer = new javax.swing.JButton();
        sep3 = new javax.swing.JToolBar.Separator();
        btnCorrer = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelConsola = new javax.swing.JPanel();
        scrollConsola = new javax.swing.JScrollPane();
        txtError = new javax.swing.JTextArea();
        lblConsola = new javax.swing.JLabel();
        splitPanelContenedor = new javax.swing.JSplitPane();
        scrollEntrada = new javax.swing.JScrollPane();
        txtEntrada = new javax.swing.JTextPane();
        pestañasTablas = new javax.swing.JTabbedPane();
        pToken = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtTokens = new javax.swing.JTextArea();
        pTablaEstatica = new javax.swing.JPanel();
        ckbTablaEstatica = new javax.swing.JComboBox<>();
        scrollTablaEstatica = new javax.swing.JScrollPane();
        tablaEstatica = new javax.swing.JTable();
        pTablaDinamica = new javax.swing.JPanel();
        ckbTablaDinamica = new javax.swing.JComboBox<>();
        scrollTablaDinamica = new javax.swing.JScrollPane();
        tablaDinamica = new javax.swing.JTable();
        pnlAutomatas = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAnalisis = new javax.swing.JTable();
        lblAutomata = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtGramaticas = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlGraUsa = new javax.swing.JTextPane();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        mnuNuevo = new javax.swing.JMenuItem();
        Fuente = new javax.swing.JMenuItem();
        mnuAbrir = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuCerrar = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mnuGuardar = new javax.swing.JMenuItem();
        mnuGuardarC = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        ckbTablas = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        barraHerramientas.setBackground(new java.awt.Color(255, 255, 255));
        barraHerramientas.setFloatable(false);
        barraHerramientas.setRollover(true);

        sep0.setForeground(new java.awt.Color(255, 255, 255));
        sep0.setEnabled(false);
        sep0.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        sep0.setOpaque(true);
        barraHerramientas.add(sep0);

        btnAbrir.setBackground(new java.awt.Color(255, 255, 255));
        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/abrirArchivo.png"))); // NOI18N
        btnAbrir.setToolTipText("Abrir archivo");
        btnAbrir.setFocusable(false);
        btnAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAbrir.setPreferredSize(new java.awt.Dimension(40, 37));
        btnAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });
        barraHerramientas.add(btnAbrir);

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardarArchivo.png"))); // NOI18N
        btnGuardar.setToolTipText("Guardar archivo");
        btnGuardar.setFocusable(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        barraHerramientas.add(btnGuardar);

        sep1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        barraHerramientas.add(sep1);

        btnCortar.setBackground(new java.awt.Color(255, 255, 255));
        btnCortar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cortarArchivo.png"))); // NOI18N
        btnCortar.setToolTipText("Cortar");
        btnCortar.setFocusable(false);
        btnCortar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCortar.setPreferredSize(new java.awt.Dimension(40, 37));
        btnCortar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCortarActionPerformed(evt);
            }
        });
        barraHerramientas.add(btnCortar);

        btnCopiar.setBackground(new java.awt.Color(255, 255, 255));
        btnCopiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/copiarArchivo.png"))); // NOI18N
        btnCopiar.setToolTipText("Copiar");
        btnCopiar.setFocusable(false);
        btnCopiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCopiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopiarActionPerformed(evt);
            }
        });
        barraHerramientas.add(btnCopiar);

        btnPegar.setBackground(new java.awt.Color(255, 255, 255));
        btnPegar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pegarArchivo.png"))); // NOI18N
        btnPegar.setToolTipText("Pegar");
        btnPegar.setFocusable(false);
        btnPegar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPegar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPegarActionPerformed(evt);
            }
        });
        barraHerramientas.add(btnPegar);
        barraHerramientas.add(sep2);

        btnDeshacer.setBackground(new java.awt.Color(255, 255, 255));
        btnDeshacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/desHacerCambio.png"))); // NOI18N
        btnDeshacer.setToolTipText("Deshacer cambio");
        btnDeshacer.setFocusable(false);
        btnDeshacer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeshacer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeshacerActionPerformed(evt);
            }
        });
        barraHerramientas.add(btnDeshacer);

        btnRehacer.setBackground(new java.awt.Color(255, 255, 255));
        btnRehacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reHacerCambio.png"))); // NOI18N
        btnRehacer.setToolTipText("Rehacer cambio");
        btnRehacer.setFocusable(false);
        btnRehacer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRehacer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRehacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRehacerActionPerformed(evt);
            }
        });
        barraHerramientas.add(btnRehacer);
        barraHerramientas.add(sep3);

        btnCorrer.setBackground(new java.awt.Color(255, 255, 255));
        btnCorrer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/correrArchivo.png"))); // NOI18N
        btnCorrer.setToolTipText("Ejecutar archivo");
        btnCorrer.setFocusable(false);
        btnCorrer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCorrer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCorrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorrerActionPerformed(evt);
            }
        });
        barraHerramientas.add(btnCorrer);

        getContentPane().add(barraHerramientas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 40));

        jSplitPane1.setBorder(null);
        jSplitPane1.setDividerLocation(480);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(1);
        jSplitPane1.setPreferredSize(new java.awt.Dimension(1230, 650));

        panelConsola.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelConsola.setFocusable(false);
        panelConsola.setPreferredSize(new java.awt.Dimension(1230, 150));

        scrollConsola.setPreferredSize(new java.awt.Dimension(166, 50));

        txtError.setEditable(false);
        txtError.setColumns(20);
        txtError.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtError.setRows(5);
        scrollConsola.setViewportView(txtError);

        lblConsola.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblConsola.setText("Consola");

        javax.swing.GroupLayout panelConsolaLayout = new javax.swing.GroupLayout(panelConsola);
        panelConsola.setLayout(panelConsolaLayout);
        panelConsolaLayout.setHorizontalGroup(
            panelConsolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsolaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelConsolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConsolaLayout.createSequentialGroup()
                        .addComponent(scrollConsola, javax.swing.GroupLayout.PREFERRED_SIZE, 1255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addGroup(panelConsolaLayout.createSequentialGroup()
                        .addComponent(lblConsola, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1158, 1158, 1158))))
        );
        panelConsolaLayout.setVerticalGroup(
            panelConsolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsolaLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lblConsola)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollConsola, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );

        jSplitPane1.setRightComponent(panelConsola);

        splitPanelContenedor.setDividerLocation(700);
        splitPanelContenedor.setResizeWeight(1);
        splitPanelContenedor.setMinimumSize(new java.awt.Dimension(113, 120));
        splitPanelContenedor.setPreferredSize(new java.awt.Dimension(1025, 300));

        txtEntrada.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        txtEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEntradaKeyReleased(evt);
            }
        });
        scrollEntrada.setViewportView(txtEntrada);

        splitPanelContenedor.setLeftComponent(scrollEntrada);

        pestañasTablas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        pestañasTablas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pestañasTablasMousePressed(evt);
            }
        });

        pToken.setBackground(new java.awt.Color(255, 255, 255));
        pToken.setLayout(new java.awt.BorderLayout());

        txtTokens.setColumns(20);
        txtTokens.setRows(5);
        jScrollPane2.setViewportView(txtTokens);

        pToken.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pestañasTablas.addTab("Tokens", pToken);

        ckbTablaEstatica.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        ckbTablaEstatica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Palabras reservadas", "Operadores", "Signos" }));
        ckbTablaEstatica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbTablaEstaticaActionPerformed(evt);
            }
        });

        tablaEstatica.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tablaEstatica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lexema", "Componente Lexico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaEstatica.setRowHeight(20);
        tablaEstatica.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tablaEstatica.setSelectionForeground(new java.awt.Color(42, 42, 42));
        tablaEstatica.setShowGrid(false);
        tablaEstatica.getTableHeader().setResizingAllowed(false);
        tablaEstatica.getTableHeader().setReorderingAllowed(false);
        scrollTablaEstatica.setViewportView(tablaEstatica);

        javax.swing.GroupLayout pTablaEstaticaLayout = new javax.swing.GroupLayout(pTablaEstatica);
        pTablaEstatica.setLayout(pTablaEstaticaLayout);
        pTablaEstaticaLayout.setHorizontalGroup(
            pTablaEstaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTablaEstaticaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTablaEstaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTablaEstatica, javax.swing.GroupLayout.DEFAULT_SIZE, 1221, Short.MAX_VALUE)
                    .addComponent(ckbTablaEstatica, javax.swing.GroupLayout.Alignment.TRAILING, 0, 1221, Short.MAX_VALUE))
                .addContainerGap())
        );
        pTablaEstaticaLayout.setVerticalGroup(
            pTablaEstaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTablaEstaticaLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(ckbTablaEstatica, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollTablaEstatica))
        );

        pestañasTablas.addTab("Tabla Estática", pTablaEstatica);

        ckbTablaDinamica.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        ckbTablaDinamica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Variables", "Funciones" }));

        tablaDinamica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Valor", "Linea", "Columna"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollTablaDinamica.setViewportView(tablaDinamica);

        javax.swing.GroupLayout pTablaDinamicaLayout = new javax.swing.GroupLayout(pTablaDinamica);
        pTablaDinamica.setLayout(pTablaDinamicaLayout);
        pTablaDinamicaLayout.setHorizontalGroup(
            pTablaDinamicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTablaDinamicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTablaDinamicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTablaDinamica, javax.swing.GroupLayout.DEFAULT_SIZE, 1221, Short.MAX_VALUE)
                    .addComponent(ckbTablaDinamica, 0, 1221, Short.MAX_VALUE))
                .addContainerGap())
        );
        pTablaDinamicaLayout.setVerticalGroup(
            pTablaDinamicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTablaDinamicaLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(ckbTablaDinamica, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollTablaDinamica))
        );

        pestañasTablas.addTab("Tabla Dinamica", pTablaDinamica);

        pnlAutomatas.setLayout(new java.awt.BorderLayout());

        tblAnalisis.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        tblAnalisis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lexema", "analisis"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblAnalisis);
        if (tblAnalisis.getColumnModel().getColumnCount() > 0) {
            tblAnalisis.getColumnModel().getColumn(0).setMaxWidth(500);
        }

        pnlAutomatas.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        lblAutomata.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAutomata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Automata.png"))); // NOI18N
        pnlAutomatas.add(lblAutomata, java.awt.BorderLayout.PAGE_END);

        pestañasTablas.addTab("Automata", pnlAutomatas);

        txtGramaticas.setEditable(false);
        txtGramaticas.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jScrollPane4.setViewportView(txtGramaticas);

        pestañasTablas.addTab("Gramaticas", jScrollPane4);

        pnlGraUsa.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jScrollPane1.setViewportView(pnlGraUsa);

        pestañasTablas.addTab("Gramaticas Usadas", jScrollPane1);

        splitPanelContenedor.setRightComponent(pestañasTablas);

        jSplitPane1.setTopComponent(splitPanelContenedor);

        getContentPane().add(jSplitPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1280, 650));

        jMenuBar2.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar2.setPreferredSize(new java.awt.Dimension(189, 30));

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menuLogo.png"))); // NOI18N
        jMenu5.setEnabled(false);
        jMenu5.setFocusable(false);
        jMenu5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jMenuBar2.add(jMenu5);

        jMenu3.setText("Archivo");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        mnuNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuNuevo.setText("Nuevo");
        mnuNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNuevoActionPerformed(evt);
            }
        });
        jMenu3.add(mnuNuevo);

        Fuente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        Fuente.setText("Fuente");
        Fuente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FuenteActionPerformed(evt);
            }
        });
        jMenu3.add(Fuente);

        mnuAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuAbrir.setText("Abrir");
        mnuAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAbrirActionPerformed(evt);
            }
        });
        jMenu3.add(mnuAbrir);
        jMenu3.add(jSeparator1);

        mnuCerrar.setText("Cerrar");
        mnuCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCerrarActionPerformed(evt);
            }
        });
        jMenu3.add(mnuCerrar);
        jMenu3.add(jSeparator2);

        mnuGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuGuardar.setText("Guardar");
        mnuGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGuardarActionPerformed(evt);
            }
        });
        jMenu3.add(mnuGuardar);

        mnuGuardarC.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuGuardarC.setText("Guardar como");
        mnuGuardarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGuardarCActionPerformed(evt);
            }
        });
        jMenu3.add(mnuGuardarC);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Compilar");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jMenuBar2.add(jMenu4);

        jMenu1.setText("Ver");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jMenuItem1.setText("Analisis Léxico");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Analisis Sintactico");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        ckbTablas.setText("Visualizar tablas ");
        ckbTablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbTablasActionPerformed(evt);
            }
        });
        jMenu1.add(ckbTablas);

        jMenuBar2.add(jMenu1);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold defaultstate="collapsed" desc=" analizadorLexico ">
    private void analizarLexico() throws IOException {
        int cont = 1;

        String expr = txtEntrada.getText();
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "LINEA " + cont + "\t\t\tLEXEMA\n";
        Token t;
        while ((t = lexer.yylex()) != null) {
            tokens.add(t);
            if (cont != t.linea) {
                cont++;
                resultado += "LINEA " + cont + "\n";
            }
            if (t.tipo.equalsIgnoreCase("ERROR")) {
                errores.add("ERROR LEXICO: el lexema (" + t.lexema + ") no sigue ninguna regla preestablecida");
            }
            resultado += " <" + t.tipo + ">\t\t" + t.lexema + "\n";
            ((DefaultTableModel) tablaDinamica.getModel()).addRow(new Object[]{t.tipo, t.lexema, t.linea, t.columna});
        }
        txtTokens.setText(resultado);
    }
    //</editor-fold>

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            analizarLexico();
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    private void btnArchivoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File archivo = new File(chooser.getSelectedFile().getAbsolutePath());

        try {
            String ST = new String(Files.readAllBytes(archivo.toPath()));
            txtTokens.setText(ST);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (tokens.isEmpty()) {
            txtError.setText("ERROR SINTACTICO: No se han encontrado tokens");
            txtError.setForeground(Color.red);
        }
        String ST = txtEntrada.getText();
        Sintax s = new Sintax(tokens);

        s.parse();
        if (errores.isEmpty()) {
            txtError.setText("Analisis realizado correctamente");
            txtError.setForeground(new Color(25, 111, 61));
        } else {
            txtError.setForeground(Color.red);
            for (String st : errores) {
                txtError.setText(txtError.getText() + st + "\n");
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void mnuNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNuevoActionPerformed
        if (txtEntrada.getText().equals("")) {
            return;
        }
        int op = javax.swing.JOptionPane.showConfirmDialog(this, "Desea guardar?");
        if (op == -1 || op == 2) {
            return;
        } else if (op == 0) {
            mnuGuardarActionPerformed(evt);
        }
        txtEntrada.setText("");
        archivo = "";
    }//GEN-LAST:event_mnuNuevoActionPerformed

    private void mnuAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAbrirActionPerformed
        abrirArchivo();
    }//GEN-LAST:event_mnuAbrirActionPerformed

    private void mnuCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCerrarActionPerformed
        if (archivo == "") {
            mnuGuardarActionPerformed(evt);
        }
        System.exit(0);
    }//GEN-LAST:event_mnuCerrarActionPerformed

    private void mnuGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGuardarActionPerformed
        if (archivo.equals("")) {
            mnuGuardarCActionPerformed(evt);
        }
        try {
            /*Flujos de Bytes
            java.io.FileOutputStream fbs=new java.io.FileOutputStream("Archivo.txt");
            byte b[]=txtEditor.getText().getBytes();
            fbs.write(b);
            fbs.flush();
            fbs.close();
             */
            //Flujos de Caracteres
            java.io.FileWriter fcs = new java.io.FileWriter(archivo);
            fcs.write(txtEntrada.getText());
            fcs.flush();
            fcs.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnuGuardarActionPerformed

    private void mnuGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGuardarCActionPerformed
        javax.swing.JFileChooser f = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("TXT", "txt");
        f.setFileFilter(filtro);

        String dir = "C:\\Users\\ximen\\OneDrive\\Documentos\\NetBeansProjects\\SmartHealth_V3\\SmartHealth\\";
        File directorio = new File(dir);
        f.setCurrentDirectory(directorio);

        if (f.showSaveDialog(this) == APPROVE_OPTION && f.getFileFilter() == filtro) {
            archivo = f.getSelectedFile().getName() + ".txt";
            mnuGuardarActionPerformed(evt);
        } else {
            showMessageDialog(this, "Solo archivos de texto");
        }
    }//GEN-LAST:event_mnuGuardarCActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File archivo = new File(chooser.getSelectedFile().getAbsolutePath());

        try {
            String ST = new String(Files.readAllBytes(archivo.toPath()));
            txtTokens.setText(ST);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenu3ActionPerformed

    private void ckbTablasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbTablasActionPerformed
        validarTablas();
    }//GEN-LAST:event_ckbTablasActionPerformed

    private void btnDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeshacerActionPerformed
        try {
            administradorCambios.undo();
        } catch (Exception ex) {
            showMessageDialog(null, "No hay mas cambios que deshacer.");
        }
    }//GEN-LAST:event_btnDeshacerActionPerformed

    private void btnRehacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRehacerActionPerformed
        try {
            administradorCambios.redo();
        } catch (Exception ex) {
            showMessageDialog(null, "No hay mas cambios que rehacer.");
        }
    }//GEN-LAST:event_btnRehacerActionPerformed

    private void btnCortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCortarActionPerformed
        txtEntrada.cut();
    }//GEN-LAST:event_btnCortarActionPerformed

    private void btnCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopiarActionPerformed
        txtEntrada.copy();
    }//GEN-LAST:event_btnCopiarActionPerformed

    private void btnPegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPegarActionPerformed
        txtEntrada.paste();
    }//GEN-LAST:event_btnPegarActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        abrirArchivo();
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarArchivo();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void ckbTablaEstaticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbTablaEstaticaActionPerformed
        llenadoTEstatico(ckbTablaEstatica.getSelectedIndex());
    }//GEN-LAST:event_ckbTablaEstaticaActionPerformed

    private void pestañasTablasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pestañasTablasMousePressed
        llenadoTEstatico(ckbTablaEstatica.getSelectedIndex());
    }//GEN-LAST:event_pestañasTablasMousePressed

    private void colorear() throws IOException {
        colores.clear();
        LexerColor lexer;
        int posicion = txtEntrada.getCaretPosition();
        try {
            java.io.File entrada = new java.io.File("color.encrypter");
            java.io.FileOutputStream output = new java.io.FileOutputStream(entrada);
            txtEntrada.setText(txtEntrada.getText().replaceAll("[\r]+", ""));
            byte[] bytes = txtEntrada.getText().getBytes();
            output.write(bytes);
            lexer = new LexerColor(new BufferedReader(new InputStreamReader(new FileInputStream(entrada), "UTF8")));
            while (true) {
                TextColor color = lexer.yylex();
                if (color == null) {
                    break;
                } else {
                    colores.add(color);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
        }
        Functions.colorTextPane(colores, txtEntrada, new Color(40, 40, 40));
        txtEntrada.setCaretPosition(posicion);
    }
    private void btnCorrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorrerActionPerformed
        errores.clear();
        tokens.clear();
        txtError.setText("");
        pnlGraUsa.setText("");
        try {
            ((DefaultTableModel) tablaDinamica.getModel()).setRowCount(0);
            analizarLexico();
        } catch (IOException ex) {
            return;
        }

        if (tokens.isEmpty()) {
            txtError.setText("ERROR SINTACTICO: No se han encontrado tokens");
            txtError.setForeground(Color.red);
        }
        String ST = txtEntrada.getText();
        Sintax s = new Sintax(tokens);

        s.parse();
        if (errores.isEmpty()) {
            txtError.setText("Analisis realizado correctamente");
            txtError.setForeground(new Color(25, 111, 61));
        } else {
            txtError.setForeground(Color.red);
            for (String st : errores) {
                txtError.setText(txtError.getText() + st + "\n");
            }
        }

        if (!Sintax.gramas.isEmpty()) {
            String r = "";
            for (java.util.Map.Entry m : Sintax.gramas.entrySet()) {
                r += m.getKey() + " " + m.getValue() + "\n";
            }
            txtGramaticas.setText(r);
        }

        java.util.TreeMap<Integer, String> ordenReglas = new java.util.TreeMap<>();
        ordenReglas.putAll(Sintax.reglas);
        if (!Sintax.reglas.isEmpty()) {
            String r = "";
            for (int m : ordenReglas.keySet()) {
                r += m + " " + ordenReglas.get(m) + "\n\n";
            }
            pnlGraUsa.setText(r);
        }

    }//GEN-LAST:event_btnCorrerActionPerformed

    private void FuenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FuenteActionPerformed
        JFontChooser fc = new JFontChooser();
        JOptionPane.showMessageDialog(null, fc, "Elegir fuente", JOptionPane.PLAIN_MESSAGE);
        txtEntrada.setFont(fc.getSelectedFont());
    }//GEN-LAST:event_FuenteActionPerformed

    private void txtEntradaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntradaKeyReleased
        try {
            //if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE) {
            colorear();
            //}
        } catch (IOException ex) {
            System.out.println("Error inesperado");
        }
    }//GEN-LAST:event_txtEntradaKeyReleased

    // Funciones
    //Pendiente
    private void nuevoArchivo() {
        if (txtEntrada.getText().equals("")) {
            return;
        }
        int op = javax.swing.JOptionPane.showConfirmDialog(this, "Desea guardar?");
        if (op == -1 || op == 2) {
            return;
        } else if (op == 0) {
            guardarArchivo();
        }
        txtEntrada.setText("");
        archivo = "";
    }

    private void abrirArchivo() {
        JFileChooser fc = getJFileChooser();
        fc.setCurrentDirectory(new java.io.File(System.getProperty("user.dir")));
        fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("ARCHIVOS SMHE", "smhe", "SMHE"));
        int state = fc.showOpenDialog(this.getJFrame());
        String s1, sl;

        if (state == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            if (!(f.getName().endsWith(".smhe") || f.getName().endsWith(".SMHE"))) {
                JOptionPane.showMessageDialog(null, "Solo los archivos con extensión .smhe son admitidos");
                abrirArchivo();
                return;
            }
            try {
                archivo = f.getAbsolutePath();
                BufferedReader br = new BufferedReader(new FileReader(f));

                sl = br.readLine();
                while ((s1 = br.readLine()) != null) {
                    sl = sl + "\n" + s1;
                }
                txtEntrada.setText(sl);
                analizarLexico();
                colorear();

                br.close();    //cierra el flujo
                this.setTitle("SMHE - " + f.getName());
            } catch (IOException ex) {
                showMessageDialog(null, ex);
            }
        }
    }
    //Pendiente

    private void guardarArchivo() {
        if (archivo.equals("")) {
            guardarCArchivo();
        }
        try {
            //Flujos de Caracteres
            java.io.FileWriter fcs = new java.io.FileWriter(archivo);
            fcs.write(txtEntrada.getText());
            fcs.flush();
            fcs.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Pendiente

    private void guardarCArchivo() {
        javax.swing.JFileChooser f = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("ARCHIVOS SMHE", "smhe", "SMHE");
        f.setFileFilter(filtro);

        String dir = System.getProperty("user.dir");
        File directorio = new File(dir);
        f.setCurrentDirectory(directorio);

        if (f.showSaveDialog(this) == APPROVE_OPTION && f.getFileFilter() == filtro) {
            archivo = System.getProperty("user.dir") + "\\" + f.getSelectedFile().getName() + ".smhe";
            guardarArchivo();
        } else {
            showMessageDialog(this, "Solo archivos de texto");
        }
    }

    private void llenadoTEstatico(int index) {

        int col = 0;
        int row = 0;

        String[] palabras_reservadas = new String[]{
            "pin", "Atlas(público)", "Distance", "Device", "Temperature",
            "Admit", "Ventilate", "Dispense", "Control_Device", "LogA",
            "OpenDoor", "Exit", "Start", "End", "Codition", "Else", "Until", "Do",
            "EmptyRoom", "DeviceControl", "RegistarA",
            "int", "float", "double", "logic", "char"
        };
        palabras_reservadas = ordenarLista(palabras_reservadas);

        String[] operaciones = new String[]{
            "*", "+", "-", "/", // Aritmeticos   0-3
            "==", "<=", ">=", "<", ">", "!=", // Comparaciones 4-9
            "&&", "||", "!", // Logicos       10-12
            "*=", "+=", "/=", "-=", "=", // Asignacion    13-17
            "{", "}", "[", "]", "(", ")" // Agrupación    18-23
        };

        String[] signos = new String[]{
            "'", ";", ":", ".", ","
        };

        String[] funcionSigno = new String[]{
            "Ej1", "Ej2", "Ej3", "Ej4", "Ej5"
        };

        switch (index) {
            case -1:
                limpiarTablaEstatica();
                for (int i = 0; i < palabras_reservadas.length; i++) {
                    modeloEstatico.addRow(new Object[]{"", ""});
                    tablaEstatica.setValueAt(palabras_reservadas[i], row, col);
                    col++;
                    tablaEstatica.setValueAt("Palabra reservada", row, col);
                    row++;
                    col--;
                }
                break;
            case 0:
                limpiarTablaEstatica();
                for (int i = 0; i < palabras_reservadas.length; i++) {
                    modeloEstatico.addRow(new Object[]{"", ""});
                    tablaEstatica.setValueAt(palabras_reservadas[i], row, col);
                    col++;
                    tablaEstatica.setValueAt("Palabra reservada", row, col);
                    row++;
                    col--;
                }
                break;
            case 1:
                limpiarTablaEstatica();
                for (int i = 0; i < operaciones.length; i++) {
                    modeloEstatico.addRow(new Object[]{"", ""});
                    tablaEstatica.setValueAt(operaciones[i], row, col);
                    col++;
                    // Aritmeticos   0-3
                    // Comparaciones 4-9
                    // Logicos       10-12
                    // Asignacion    13-17
                    // Agrupación    18-23

                    if (i >= 0 && i <= 3) {
                        tablaEstatica.setValueAt("Op. Aritmetica", row, col);
                    }
                    if (i >= 4 && i <= 9) {
                        tablaEstatica.setValueAt("Op. Comparativa", row, col);
                    }
                    if (i >= 10 && i <= 12) {
                        tablaEstatica.setValueAt("Op. Logica", row, col);
                    }
                    if (i >= 13 && i <= 17) {
                        tablaEstatica.setValueAt("Dec. Asignación", row, col);
                    }
                    if (i >= 18 && i <= 23) {
                        tablaEstatica.setValueAt("Dec. Agrupación", row, col);
                    }

                    row++;
                    col--;
                }
                break;
            case 2:
                limpiarTablaEstatica();
                for (int i = 0; i < signos.length; i++) {
                    modeloEstatico.addRow(new Object[]{"", ""});
                    tablaEstatica.setValueAt(signos[i], row, col);
                    col++;
                    tablaEstatica.setValueAt(funcionSigno[i], row, col);
                    row++;
                    col--;
                }
                break;
            default:
                System.out.println("hello");
        }

    }

    public void limpiarTablaEstatica() {
        modeloEstatico.getDataVector().removeAllElements();
        modeloEstatico.fireTableDataChanged();
        revalidate();
    }

    public void limpiarTablaDinamica() {
        modeloDinamico.getDataVector().removeAllElements();
        modeloDinamico.fireTableDataChanged();
        revalidate();
    }

    private void validarTablas() {
        int div = 0;
        div = splitPanelContenedor.getDividerLocation();

        pestañasTablas.setVisible(ckbTablas.getState());
        splitPanelContenedor.setDividerLocation(600);
        revalidate();
    }

    public String[] ordenarLista(String[] lista) {
        for (int i = 0; i < lista.length; i++) {
            for (int j = i + 1; j < lista.length; j++) {
                if (lista[i].compareTo(lista[j]) > 0) {
                    String temp = lista[i];
                    lista[i] = lista[j];
                    lista[j] = temp;
                }
            }
        }
        return lista;
    }

    private static FileFilter textFileFilter = new FileFilter() {

        @Override
        public boolean accept(File f) {
            //acepta directorios y archivos de extensión .txt
            return f.isDirectory() || f.getName().toLowerCase().endsWith("sh");
        }

        @Override
        public String getDescription() {
            //la descripción del tipo de archivo aceptado
            return "Archivos SH";
        }
    };

    private static JFileChooser getJFileChooser() {
        JFileChooser fc = new JFileChooser();                     //construye un JFileChooser
        fc.setDialogTitle("SH - Elige un archivo:");    //se le establece un título
        fc.setMultiSelectionEnabled(false);                       //desactiva la multi-selección
        fc.setFileFilter(textFileFilter);                         //aplica un filtro de extensiones
        return fc;    //retorna el JFileChooser
    }

    JFrame getJFrame() {
        return this;
    }

    // Codigo main
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Fuente;
    private javax.swing.JToolBar barraHerramientas;
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCopiar;
    private javax.swing.JButton btnCorrer;
    private javax.swing.JButton btnCortar;
    private javax.swing.JButton btnDeshacer;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnPegar;
    private javax.swing.JButton btnRehacer;
    private javax.swing.JComboBox<String> ckbTablaDinamica;
    private javax.swing.JComboBox<String> ckbTablaEstatica;
    private javax.swing.JCheckBoxMenuItem ckbTablas;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblAutomata;
    private javax.swing.JLabel lblConsola;
    private javax.swing.JMenuItem mnuAbrir;
    private javax.swing.JMenuItem mnuCerrar;
    private javax.swing.JMenuItem mnuGuardar;
    private javax.swing.JMenuItem mnuGuardarC;
    private javax.swing.JMenuItem mnuNuevo;
    private javax.swing.JPanel pTablaDinamica;
    private javax.swing.JPanel pTablaEstatica;
    private javax.swing.JPanel pToken;
    private javax.swing.JPanel panelConsola;
    private javax.swing.JTabbedPane pestañasTablas;
    private javax.swing.JPanel pnlAutomatas;
    private javax.swing.JTextPane pnlGraUsa;
    private javax.swing.JScrollPane scrollConsola;
    private javax.swing.JScrollPane scrollEntrada;
    private javax.swing.JScrollPane scrollTablaDinamica;
    private javax.swing.JScrollPane scrollTablaEstatica;
    private javax.swing.JToolBar.Separator sep0;
    private javax.swing.JToolBar.Separator sep1;
    private javax.swing.JToolBar.Separator sep2;
    private javax.swing.JToolBar.Separator sep3;
    private javax.swing.JSplitPane splitPanelContenedor;
    private javax.swing.JTable tablaDinamica;
    private javax.swing.JTable tablaEstatica;
    public static javax.swing.JTable tblAnalisis;
    public static javax.swing.JTextPane txtEntrada;
    public static javax.swing.JTextArea txtError;
    public static javax.swing.JTextPane txtGramaticas;
    private javax.swing.JTextArea txtTokens;
    // End of variables declaration//GEN-END:variables
}
