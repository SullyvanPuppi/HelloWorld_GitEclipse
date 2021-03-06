package apresentacao;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

import negocios.Cliente;
import negocios.Contato;

public class FrmCadastroClienteCompleto extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3160001269181371L;

	//--Instancia��o do formul�rio de contato
	FrmCadastroContato formCadastroContato;
	
	private JPanel jContentPane = null;
	//Cria��o da �rea de trabalho do formul�rio
	JDesktopPane desk = null;
	
	JTabbedPane abaPrincipal = null;
	JPanel abaDados = null;
	JPanel abaContatos = null;

	//Direitos de usu�rio determinado pelo grupo
	private int permissao;
	private JLabel lblDivisao = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JButton btnAlterar = null;
	private JButton btnRelatorio = null;
	private JButton btnAdicionarContato = null;
	private JButton btnAlterarContato = null;
	private JButton btnRemoverContato = null;
	private JButton btnRelContatos = null;
	
	private Box contato = Box.createVerticalBox();
	private Box linhaContato = Box.createHorizontalBox();	
	
	//--Campos de cadastro completo
	private JFormattedTextField txtNmCliente = null;
	private JFormattedTextField txtCnpjCliente = null;
	private JComboBox cmbSegmento = null;
	private JTextArea txtDsAtividade = null;
	private JFormattedTextField txtClienteDesde = null;
	private JFormattedTextField txtDtCadastro = null;
	private JFormattedTextField txtTelCliente = null;
	private JFormattedTextField txtFaxCliente = null;
	private JFormattedTextField txtEmail = null;
	private JFormattedTextField txtSite = null;
	private JTextArea txtObs = null;
	private JTable tbContatos = null;
	private JScrollPane jspContatos = null;
	private JFormattedTextField txtRazaoCliente = null;
	private JFormattedTextField txtIeCliente = null;
	private JTextField txtEndereco = null;
	private JFormattedTextField txtEnderecoNumero = null;
	private JFormattedTextField txtEnderecoComplemento = null;
	private JFormattedTextField txtEnderecoBairro = null;
	private JFormattedTextField txtEnderecoCidade = null;
	private JComboBox cmbEnderecoEstado = null;
	private JFormattedTextField txtEnderecoCep = null;
	private JFormattedTextField txtTelefone2 = null;
	private JScrollPane jspTxtDsAtividade = null;

	public int getPermissao(){
		return this.permissao;
	}
	//---Ajusta permiss�o no sistema
	public void ajustaPermissao(){
		if(getPermissao()==1){//Administrdor		

		}else if(getPermissao()==2){//Secretaria

		}else if(getPermissao()==3){//Convidado

		}
	}
	private String selecionado = "";
	private boolean status = false;

	//----Atributo para armazenar os itens da tabela(linhasXcolunas)	
	DefaultTableModel tabela;

	//----Retorna a tabela (linhasXcolunas)
	public DefaultTableModel getTabela(){
		return this.tabela;
	}
	//--Retornar sele��o
	public String getSelecionado(){
		return this.selecionado;
	}
	public void setSelecionado(String selecao){
		this.selecionado = selecao;
	}
	//----M�todo para construir tabela de pesquisa
	public void construirTabela(){
		tbContatos = new JTable(getTabela());
		jspContatos = new JScrollPane( tbContatos );
		jspContatos.setPreferredSize(new Dimension(500, 500));
		contato.add(jspContatos);
		contato.add(linhaContato);
	}
//	----Consultar camada de neg�cios - Classe Funcion�rio
	public void consultarContatos(String nmEmpresa){
		Contato consultar = new Contato();
		consultar.consultarContatosEmpresa(nmEmpresa.trim());
		this.tabela = consultar.getTabelaContatosEmpresa();	
		if(tabela.getRowCount()<=0){
			Object linha[] = new Object[2];
			linha[0] = "";
			linha[1] = "";
			tabela.addRow(linha);
			construirTabela();			
		}else{
			construirTabela();			
		}
	}
	//---Classe respons�vel por formatar m�rcaras de entrada de campos espec�ficos
	private MaskFormatter setMascara(String mascara){  
		MaskFormatter mask = null;  
		try {  
			mask = new MaskFormatter(mascara);  
			mask.setPlaceholderCharacter(' ');  
		}  
		catch (java.text.ParseException exc) { 

		}  
		return mask;  
	}
	public void ajustaDados(String cnpj, String tipo){
		Cliente consultar = new Cliente();
		consultar.consultarCliente(cnpj, tipo);
		txtNmCliente.setText(consultar.getTxtNmCliente());
		txtCnpjCliente.setText(consultar.getTxtCnpjCliente());
		cmbSegmento.setSelectedItem((String) consultar.getCmbSegmento());
		txtDsAtividade.setText(consultar.getTxtDsAtividade());
		txtClienteDesde.setText(consultar.getTxtClienteDesde());
		txtDtCadastro.setText(consultar.getTxtDtCadastro());
		txtTelCliente.setText(consultar.getTxtTelCliente());
		txtFaxCliente.setText(consultar.getTxtFaxCliente());
		txtEmail.setText(consultar.getTxtEmail());
		txtSite.setText(consultar.getTxtSite());
		txtObs.setText(consultar.getTxtObs());
		txtRazaoCliente.setText(consultar.getTxtRazaoCliente());
		txtIeCliente.setText(consultar.getTxtIeCliente());
		txtEndereco.setText(consultar.getTxtEndereco());
		txtEnderecoNumero.setText(consultar.getTxtEnderecoNumero());
		txtEnderecoComplemento.setText(consultar.getTxtEnderecoComplemento());;
		txtEnderecoBairro.setText(consultar.getTxtEnderecoBairro());
		txtEnderecoCidade.setText(consultar.getTxtEnderecoCidade());;
		cmbEnderecoEstado.setSelectedItem((String) consultar.getCmbEnderecoEstado());;
		txtEnderecoCep.setText(consultar.getTxtEnderecoCep());
		txtTelefone2.setText(consultar.getTxtTelefone2());
	}
	private int op;

	public int getOp(){
		return op;
	}
	private void desabilitaCampos(){
		txtNmCliente.setEditable(false);
		txtCnpjCliente.setEditable(false);
		cmbSegmento.setEnabled(false);
		txtDsAtividade.setEditable(false);
		txtClienteDesde.setEditable(false);
		txtTelCliente.setEditable(false);
		txtFaxCliente.setEditable(false);
		txtEmail.setEditable(false);
		txtSite.setEditable(false);
		txtObs.setEditable(false);
		txtRazaoCliente.setEditable(false);
		txtIeCliente.setEditable(false);
		txtEndereco.setEditable(false);
		txtEnderecoNumero.setEditable(false);
		txtEnderecoComplemento.setEditable(false);
		txtEnderecoBairro.setEditable(false);
		txtEnderecoCidade.setEditable(false);
		cmbEnderecoEstado.setEnabled(false);
		txtEnderecoCep.setEditable(false);
		txtTelefone2.setEditable(false);
	}
	private void habilitaCampos(){
		cmbSegmento.setEnabled(true);
		txtDsAtividade.setEditable(true);
		txtClienteDesde.setEditable(true);
		txtTelCliente.setEditable(true);
		txtFaxCliente.setEditable(true);
		txtEmail.setEditable(true);
		txtSite.setEditable(true);
		txtObs.setEditable(true);
		txtRazaoCliente.setEditable(true);
		txtIeCliente.setEditable(true);
		txtEndereco.setEditable(true);
		txtEnderecoNumero.setEditable(true);
		txtEnderecoComplemento.setEditable(true);
		txtEnderecoBairro.setEditable(true);
		txtEnderecoCidade.setEditable(true);
		cmbEnderecoEstado.setEnabled(true);
		txtEnderecoCep.setEditable(true);
		txtTelefone2.setEditable(true);
	}
	/**
	 * This is the xxx default constructor
	 */
	public FrmCadastroClienteCompleto(JDesktopPane desktop, int permissao, int op, String nmEmpresa, String tipo) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		this.op = op;
		consultarContatos(nmEmpresa);
		initialize();
		if(getOp()==0){//Novo cadastro
			Cliente novo = new Cliente();
			txtDtCadastro.setText(novo.getHoje());
			txtDtCadastro.setEnabled(false);
			btnConfirmar.setEnabled(true);
			btnAlterar.setEnabled(false);
		}else{//Exibir dados
			status = true;
			ajustaDados(nmEmpresa, tipo);
			txtDtCadastro.setEnabled(false);
			btnConfirmar.setEnabled(false);
			btnAlterar.setEnabled(true);
			desabilitaCampos();
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Cadastro de empresa cliente completo");
		
		abaPrincipal = new JTabbedPane();
		abaDados = new JPanel();
		abaContatos = new JPanel();
		
		JLabel lblNmCliente = new JLabel("Nome da empresa");
		txtNmCliente = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblCnpjCliente = new JLabel("CNPJ");
		txtCnpjCliente = new JFormattedTextField(setMascara("##.###.###/####-##"));
		JLabel lblSegmento = new JLabel("Segmento");
		cmbSegmento = new JComboBox(getSegmentos());
		JLabel lblDsAtividade = new JLabel("Descri��o da atividade");
		txtDsAtividade = new JTextArea();
		jspTxtDsAtividade = new JScrollPane(txtDsAtividade);
		txtDsAtividade.setPreferredSize(new Dimension(40, 100));		
		JLabel lblClienteDesde = new JLabel("Cliente desde");
		txtClienteDesde = new JFormattedTextField(setMascara("##/##/####"));
		JLabel lblDtCadastro = new JLabel("Cadastro");
		txtDtCadastro = new JFormattedTextField(setMascara("##/##/####"));
		JLabel lblTelCliente = new JLabel("Telefone");
		txtTelCliente = new JFormattedTextField(setMascara("(##)####-####"));
		JLabel lblFaxCliente = new JLabel("Fax");
		txtFaxCliente = new JFormattedTextField(setMascara("(##)####-####"));
		JLabel lblEmail = new JLabel("E-mail");
		txtEmail = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblSite = new JLabel("Site");
		txtSite = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblObs = new JLabel("Observa��es");
		txtObs = new JTextArea();
		JScrollPane jspTxtObs = new JScrollPane(txtObs);
		jspTxtObs.setPreferredSize(new Dimension(40, 100));
		btnAdicionarContato = new JButton("Novo");
		btnAdicionarContato.setToolTipText("Adicionar novo contato");
		btnAlterarContato = new JButton("Abrir");
		btnAlterarContato.setToolTipText("Abrir item selecionado");
		btnRemoverContato = new JButton("Remover");
		btnRemoverContato.setToolTipText("Remover item selecionado");
		btnRelContatos = new JButton("Relat�rio");
		btnRelContatos.setToolTipText("Gerar relat�rio com os par�metros selecionados");
		
		//--Itens do cadastro completo
		JLabel lblRazaoCliente = new JLabel("Raz�o social");
		txtRazaoCliente = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblIeCliente = new JLabel("Inscri��o estadual");
		txtIeCliente = new JFormattedTextField(setMascara("###.###.###.###"));
		JLabel lblEndereco = new JLabel("Endere�o");
		txtEndereco = new JTextField("",60);
		JLabel lblEnderecoNumero = new JLabel("N�mero");
		txtEnderecoNumero = new JFormattedTextField(setMascara("#####"));
		JLabel lblEnderecoComplemento = new JLabel("Complemento");
		txtEnderecoComplemento = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblEnderecoBairro = new JLabel("Bairro");
		txtEnderecoBairro = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblEnderecoCidade = new JLabel("Cidade");
		txtEnderecoCidade = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblEnderecoEstado = new JLabel("Estado");
		cmbEnderecoEstado = new JComboBox(getEstados());
		JLabel lblEnderecoCep = new JLabel("Cep");
		txtEnderecoCep = new JFormattedTextField(setMascara("##.###-###"));
		JLabel lblTelefone2 = new JLabel("Telefone");
		txtTelefone2 = new JFormattedTextField(setMascara("(##)####-####"));
		
		lblDivisao = new JLabel("___________________________________________________________________________________________");
		btnRelatorio = new JButton("Relat�rio");
		btnRelatorio.setToolTipText("Gerar relat�rio com par�metros selecionados");
		btnAlterar = new JButton("Alterar");
		btnAlterar.setToolTipText("Alterar dados cadastrados");
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setToolTipText("Confirmar dados para cadastro");
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar cadastro e fechar");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaDois = Box.createHorizontalBox();
		Box linhaTresA = Box.createHorizontalBox();
		Box linhaTres = Box.createHorizontalBox();
		Box linhaQuatro = Box.createHorizontalBox();
		Box linhaCinco = Box.createHorizontalBox();
		Box linhaSeis = Box.createHorizontalBox();
		Box linhaSete = Box.createHorizontalBox();
		Box linhaOito = Box.createHorizontalBox();
		Box linhaNove = Box.createHorizontalBox();
		
		Box linhaA = Box.createHorizontalBox();
		Box linhaC = Box.createHorizontalBox();
		Box linhaD = Box.createHorizontalBox();
		Box linhaE = Box.createHorizontalBox();
		Box linhaF = Box.createHorizontalBox();
		
		linhaD.add(lblEndereco);
		linhaD.add(txtEndereco);
		linhaE.add(lblEnderecoNumero);
		linhaE.add(txtEnderecoNumero);
		linhaE.add(Box.createHorizontalStrut(5));
		linhaE.add(lblEnderecoComplemento);
		linhaE.add(txtEnderecoComplemento);
		linhaE.add(Box.createHorizontalStrut(5));
		linhaE.add(lblEnderecoCep);
		linhaE.add(txtEnderecoCep);
		linhaF.add(lblEnderecoBairro);
		linhaF.add(txtEnderecoBairro);
		linhaF.add(Box.createHorizontalStrut(5));
		linhaF.add(lblEnderecoCidade);
		linhaF.add(txtEnderecoCidade);
		linhaF.add(Box.createHorizontalStrut(5));
		linhaF.add(lblEnderecoEstado);
		linhaF.add(cmbEnderecoEstado);		
		
		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();

		linhaUm.add(lblNmCliente);
		linhaUm.add(txtNmCliente);
		linhaC.add(lblRazaoCliente);
		linhaC.add(txtRazaoCliente);
		linhaDois.add(lblCnpjCliente);
		linhaDois.add(txtCnpjCliente);
		linhaDois.add(Box.createHorizontalStrut(5));
		linhaDois.add(lblIeCliente);
		linhaDois.add(txtIeCliente);
		linhaA.add(lblSegmento);
		linhaA.add(cmbSegmento);
		linhaTresA.add(lblDsAtividade);
		linhaTres.add(jspTxtDsAtividade);
		linhaTres.add(Box.createHorizontalStrut(5));
		linhaQuatro.add(lblClienteDesde);
		linhaQuatro.add(txtClienteDesde);
		linhaQuatro.add(Box.createHorizontalStrut(5));
		linhaQuatro.add(lblDtCadastro);
		linhaQuatro.add(txtDtCadastro);
		linhaCinco.add(lblTelCliente);
		linhaCinco.add(txtTelCliente);
		linhaCinco.add(Box.createHorizontalStrut(5));
		linhaCinco.add(lblFaxCliente);
		linhaCinco.add(txtFaxCliente);
		linhaCinco.add(Box.createHorizontalStrut(5));
		linhaCinco.add(lblTelefone2);
		linhaCinco.add(txtTelefone2);
		linhaCinco.add(Box.createHorizontalStrut(5));
		linhaSeis.add(lblEmail);
		linhaSeis.add(txtEmail);
		linhaSeis.add(Box.createHorizontalStrut(5));
		linhaSeis.add(lblSite);
		linhaSeis.add(txtSite);
		linhaSete.add(lblObs);
		linhaOito.add(lblObs);
		linhaNove.add(jspTxtObs);
		
		linhaDoze.add(lblDivisao);
		linhaTreze.add(btnRelatorio);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnAlterar);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnConfirmar);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnCancelar);

		Box linhas = Box.createVerticalBox();
		linhas.add(linhaUm);
		linhas.add(Box.createVerticalStrut(5));		
		linhas.add(linhaC);
		linhas.add(Box.createVerticalStrut(5));		
		linhas.add(linhaDois);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaA);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaTresA);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaTres);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaQuatro);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaCinco);	
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaSeis);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaSete);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaD);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaE);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaF);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaOito);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaNove);
								
		linhaContato.add(btnRelContatos);
		linhaContato.add(btnAlterarContato);
		linhaContato.add(btnAdicionarContato);
		linhaContato.add(btnRemoverContato);
				
		Box caixa = Box.createVerticalBox();
		
		abaPrincipal.addTab("Dados da empresa", null, abaDados, null);
		abaPrincipal.addTab("Contatos", null, abaContatos, null);
		
		abaDados.add(linhas);
		abaContatos.add(contato);

		caixa.add(abaPrincipal);
		caixa.add(linhaDoze);
		caixa.add(Box.createVerticalStrut(10));
		caixa.add(linhaTreze);
		
		
		jContentPane = new JPanel();
		jContentPane.add(caixa);
		getContentPane().add(jContentPane);
		pack();
		
		txtClienteDesde.setInputVerifier(new InputVerifier() {
			public boolean verify(JComponent component) {
				if(((JTextComponent) component).getText().equals("  /  /    ")){
					txtClienteDesde.setText("");
					return ((JFormattedTextField) txtClienteDesde).isEditValid();
				}else{
					Cliente consultar = new Cliente();
					if(consultar.validaDataCadastro(txtClienteDesde.getText(), txtDtCadastro.getText())>0){
						txtClienteDesde.setText("");
						txtClienteDesde.grabFocus();
						return false;
					}else{
						return ((JFormattedTextField) txtClienteDesde).isEditValid();
					}
				}				
			}
		});
		tbContatos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbContatos.getValueAt(tbContatos.getSelectedRow(), 0));
			}
		});
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cadastrar = new Cliente();
				cadastrar.ajustaDados(txtNmCliente.getText(), txtCnpjCliente.getText(), (String) cmbSegmento.getSelectedItem(), txtDsAtividade.getText(), txtClienteDesde.getText(), txtDtCadastro.getText(), txtTelCliente.getText(), txtFaxCliente.getText(), txtEmail.getText(), txtSite.getText(), txtObs.getText(), txtRazaoCliente.getText(), txtIeCliente.getText(), txtEndereco.getText(), txtEnderecoNumero.getText(), txtEnderecoComplemento.getText(), txtEnderecoBairro.getText(), txtEnderecoCidade.getText(), (String) cmbEnderecoEstado.getSelectedItem(), txtEnderecoCep.getText(), txtTelefone2.getText());
				if(cadastrar.validarCompleto()>0){
					JOptionPane.showMessageDialog(null, "Encontrada inconsist�ncias nos dados informados:\n"+cadastrar.getMsgStatus(),"Erro",2);
				}else{
					if(cadastrar.verificaExiste()==0){
						cadastrar.cadastrarCompleto();
						desabilitaCampos();
						btnConfirmar.setEnabled(false);
						btnAlterar.setEnabled(true);
						status = true;
					}else{
						if(op==1){
							if (JOptionPane.showConfirmDialog(new JFrame(),
									"Deseja alterar as informa��es da empresa cliente?", "Confirma��o",
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
								cadastrar.alterarCompleto();
								desabilitaCampos();
								btnConfirmar.setEnabled(false);
								btnAlterar.setEnabled(true);
							}	
						}else{
							JOptionPane.showMessageDialog(null, "Empresa cliente ja est� cadastrada","Erro",2);
						}						
					}	
				}
			}});
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitaCampos();
				btnConfirmar.setEnabled(true);
				btnAlterar.setEnabled(false);				
			}});
		btnRelatorio.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Cliente gerar = new Cliente();
				gerar.gerarRelatorio(txtCnpjCliente.getText().trim(), txtNmCliente.getText().trim()).show();
			}});
		btnAdicionarContato.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(status){
					if ((formCadastroContato == null) || (!formCadastroContato.isVisible())) {
						formCadastroContato = new FrmCadastroContato(desk, getPermissao(), 0, "", txtNmCliente.getText(), txtCnpjCliente.getText(), "Cliente","");
						formCadastroContato.setLocation(300,300);
						formCadastroContato.show();
						atualizaContatos();
					}	
				}else{
					JOptionPane.showMessageDialog(null,"Cadastre primeiro a empresa cliente antes de adicionar contatos � ela.","Notifica��o", 1);
				}				
			}});
		btnAlterarContato.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(getSelecionado().equals("")){
					JOptionPane.showMessageDialog(null,"Selecione um contato para poder visualizar seus dados","Notifica��o", 1);
				}else{
					if ((formCadastroContato == null) || (!formCadastroContato.isVisible())) {
						formCadastroContato = new FrmCadastroContato(desk, getPermissao(), 1, getSelecionado().trim(), txtNmCliente.getText().trim(), txtCnpjCliente.getText().trim(), "Cliente","");
						formCadastroContato.setLocation(300,300);
						formCadastroContato.show();
						atualizaContatos();
					}	
				}				
			}});
		btnRemoverContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getSelecionado().equals("")){
					JOptionPane.showMessageDialog(null,"Selecione um contato para poder remov�-lo","Notifica��o",1);
				}else{
					String nome = (String) tbContatos.getValueAt(tbContatos.getSelectedRow(), 0);
					Contato remover = new Contato();
					if (JOptionPane.showConfirmDialog(new JFrame(),
							"Tem certeza que deseja remover este contato?\n\nContato: "+nome, "Confirma��o",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						remover.remover(getSelecionado(), txtNmCliente.getText().trim(), "", "Cliente");
						atualizaContatos();
					}	
				}					
			}});
		btnRelContatos.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Cliente gerar = new Cliente();
				gerar.gerarRelatorioContatos(txtCnpjCliente.getText().trim(), txtNmCliente.getText().trim()).show();
			}});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
	}
	private void atualizaContatos(){
		jspContatos.remove(tbContatos);
		contato.remove(jspContatos);
		//contato.remove(linhaContato);
		//abaContatos.remove(contato);
		consultarContatos(txtNmCliente.getText().trim());
		abaContatos.add(contato);
		contato.doLayout();
		abaContatos.doLayout();
		//abaPrincipal.doLayout();
		//jContentPane.doLayout();
		tbContatos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbContatos.getValueAt(tbContatos.getSelectedRow(), 0));
			}
		});
	}
	public DefaultComboBoxModel getSegmentos(){
		DefaultComboBoxModel segmentos = new DefaultComboBoxModel();
		//Lista de segmentos
		segmentos.addElement(new String("--------------------------------"));
		segmentos.addElement(new String("00 - N�o informado"));
		segmentos.addElement(new String("01 - Alimentos"));
		segmentos.addElement(new String("02 - Automobil�stica"));
		segmentos.addElement(new String("03 - Bebidas"));
		segmentos.addElement(new String("04 - Celulose"));
		segmentos.addElement(new String("05 - Cimentos"));
		segmentos.addElement(new String("06 - Siderurgia"));
		segmentos.addElement(new String("07 - Escadas rolantes"));
		segmentos.addElement(new String("08 - Fertilizantes"));
		segmentos.addElement(new String("09 - Fumageira"));
		segmentos.addElement(new String("10 - Fundi��o"));
		segmentos.addElement(new String("11 - Graneiro"));
		segmentos.addElement(new String("12 - Madeira"));
		segmentos.addElement(new String("13 - Minera��o"));
		segmentos.addElement(new String("14 - Qu�mico"));
		segmentos.addElement(new String("15 - Diversos"));
		segmentos.addElement(new String("16 - Revenda"));
		segmentos.addElement(new String("17 - �leos"));
		segmentos.addElement(new String("18 - Ra��es"));
		segmentos.addElement(new String("19 - G�s"));
		segmentos.addElement(new String("20 - Usina"));
		return segmentos;
	}
	public DefaultComboBoxModel getEstados(){
		DefaultComboBoxModel estados = new DefaultComboBoxModel();
		//Lista de estados brasileiros
		estados.addElement(new String("--"));
		estados.addElement(new String("AC"));
		estados.addElement(new String("AL"));
		estados.addElement(new String("AP"));
		estados.addElement(new String("AM"));
		estados.addElement(new String("BA"));
		estados.addElement(new String("CE"));
		estados.addElement(new String("ES"));
		estados.addElement(new String("GO"));
		estados.addElement(new String("MA"));
		estados.addElement(new String("MT"));
		estados.addElement(new String("MS"));
		estados.addElement(new String("MG"));
		estados.addElement(new String("PA"));
		estados.addElement(new String("PB"));
		estados.addElement(new String("PR"));
		estados.addElement(new String("PE"));
		estados.addElement(new String("PI"));
		estados.addElement(new String("RJ"));
		estados.addElement(new String("RN"));
		estados.addElement(new String("RS"));
		estados.addElement(new String("RO"));
		estados.addElement(new String("RR"));
		estados.addElement(new String("SC"));
		estados.addElement(new String("SP"));
		estados.addElement(new String("SE"));
		estados.addElement(new String("TO"));
		estados.addElement(new String("DF"));
		return estados;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
