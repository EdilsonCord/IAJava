/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulacao_imagens;

/**
 *
 * @author Avell
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Imagens extends JFrame implements ActionListener {
    
        private ObjetosGraficos painel;
	private JMenuItem abrirItem;
	private JMenuItem sairItem;
	private JMenuItem itemOpaco;
	private JMenuItem intensificarItem;
	private JMenuItem iluminarItem;
	private JMenuItem detectarBorda;
	private JMenuItem inverterCor;
	private JMenuItem rotacionar;
	private JMenuItem imagemManipulada;
	private JMenuItem gravarRGB;
	private JMenuItem apagarRGB;
	private JMenuItem cores;
	private JMenuItem redeNeural1;
	private JMenuItem redeNeural2;
	private JMenuItem redeNeural3;
	private JMenuItem redeNeural4;
	double pixel;
	
	public Imagens() {
		setTitle("Processamento de Imagens");
		setSize(300, 400);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		Container container = getContentPane();
		painel = new ObjetosGraficos();
		container.add(painel, "Center");
		
		JMenu fileMenu = new JMenu("Arquivo");
		abrirItem = new JMenuItem("Abrir");
		abrirItem.addActionListener(this);
		fileMenu.add(abrirItem);
		sairItem = new JMenuItem("Sair");
		sairItem.addActionListener(this);
		fileMenu.add(sairItem);
		
		JMenu editMenu = new JMenu("Editar");
		itemOpaco = new JMenuItem("Opaco");
		itemOpaco.addActionListener(this);
		editMenu.add(itemOpaco);
		intensificarItem = new JMenuItem("Intensificar");
		intensificarItem.addActionListener(this);
		editMenu.add(intensificarItem);
		iluminarItem = new JMenuItem("Iluminar");
		iluminarItem.addActionListener(this);
		editMenu.add(iluminarItem);
		detectarBorda = new JMenuItem("Detectar Borda");
		detectarBorda.addActionListener(this);
		editMenu.add(detectarBorda);
		inverterCor = new JMenuItem("Inverte Cor");
		inverterCor.addActionListener(this);
		editMenu.add(inverterCor);
		rotacionar = new JMenuItem("Rotacionar");
		rotacionar.addActionListener(this);
		editMenu.add(rotacionar);
		gravarRGB = new JMenuItem("Gravar RGB");
		gravarRGB.addActionListener(this);
		editMenu.add(gravarRGB);
		apagarRGB = new JMenuItem("Apagar RGB");
		apagarRGB.addActionListener(this);
		editMenu.add(apagarRGB);
		imagemManipulada = new JMenuItem("Grava Imagem");
		imagemManipulada.addActionListener(this);
		editMenu.add(imagemManipulada);
		cores = new JMenuItem("Mostra RGB");
		cores.addActionListener(this);
		editMenu.add(cores);
		redeNeural1 = new JMenuItem("Treina Rede Neural");
		redeNeural1.addActionListener(this);
		editMenu.add(redeNeural1);
		redeNeural2 = new JMenuItem("Testa Rede Neural");
		redeNeural2.addActionListener(this);
		editMenu.add(redeNeural2);
		redeNeural3 = new JMenuItem("Imagem Rede Neural");
		redeNeural3.addActionListener(this);
		editMenu.add(redeNeural3);
		redeNeural4 = new JMenuItem("Testa Generalização");
		redeNeural4.addActionListener(this);
		editMenu.add(redeNeural4);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		setJMenuBar(menuBar);
	}
	
	int xPos, yPos, u, i;
	
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.red);
		g.drawString("x= "+ xPos + " y= "+ yPos, xPos, yPos);
		g.setColor(Color.orange);
		g.fillRect(u,i,8,8);
		repaint();
	}
	
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		if(source == abrirItem) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));
			
			chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
				public boolean accept(File f) {
					String nome = f.getName().toLowerCase();
					return nome.endsWith(".gif")
							|| nome.endsWith(".jpg")
							|| nome.endsWith(".jpeg")
							|| f.isDirectory();
				}
				public String getDescription() {
					return "Imagem do Arquivo";
				}
			});
			
			int r = chooser.showOpenDialog(this);
			if(r == JFileChooser.APPROVE_OPTION) {
				String name = chooser.getSelectedFile().getAbsolutePath();
				painel.loadImage(name);
			}
		}
		else if(source == sairItem) System.exit(0);
		else if(source == itemOpaco) painel.blur();
		else if(source == intensificarItem) painel.ilumina();
		else if(source == iluminarItem) painel.borda();
		else if(source == detectarBorda) painel.inverte();
		else if(source == inverterCor) painel.rotaciona();
		else if(source == rotacionar) painel.converte();
		else if(source == cores) painel.gravaArquivo();
		else if(source == gravarRGB) painel.apagaArquivo();
		else if(source == apagarRGB) painel.encaminha();
		else if(source == imagemManipulada) painel.treinaRedeNeural();
		else if(source == redeNeural1) painel.treinaRedeNeural();
		else if(source == redeNeural2) painel.testeRedeNeural();
		else if(source == redeNeural3) painel.geraImagemRedeNeural();
		else if(source == redeNeural4) painel.testeGeneralizacao();
	}
	
    
}
