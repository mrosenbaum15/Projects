module datapath
(
        input logic Clk,
        input logic Reset,
        input logic LD_MAR, LD_MDR, LD_IR, LD_BEN, LD_CC, LD_REG, LD_PC, LD_LED,
        input logic GatePC, GateMDR, GateALU, GateMARMUX,
        input logic [1:0] PCMUX, ADDR2MUX, ALUK,
        input logic DRMUX, SR1MUX, SR2MUX, ADDR1MUX,
        input logic MIO_EN,
        input logic [15:0] input_MDR,
        output logic [15:0] output_MAR, output_MDR, output_IR, output_PC,
        output logic BEN
);

logic [15:0] data_bus_local, output_SR1_local, output_SR2_local;
logic [15:0] output_MAR_local, output_MDR_local, output_IR_local, output_PC_local;
logic [15:0] output_PCMUX_local, output_ADDR2MUX_local, output_ALU_local;
logic [15:0] output_DRMUX_local, output_MDRMUX_local, output_SR2MUX_local, output_ADDR1MUX_local;
logic [15:0] input_reg_system_local;

logic output_branch_en_local;

two_one_MUX mdr_mux0( .in0(data_bus_local), .in1(input_MDR), .select(MIO_EN), .mux_output(output_MDRMUX_local) );

two_one_MUX addr1_mux1( .in0(output_PC_local), .in1(output_SR1_local), .select(ADDR1MUX), .mux_output(output_ADDR1MUX_local) );

logic [15:0] state_10;
assign state_10 = {{10{output_IR_local[5]}}, output_IR_local[5:0]};

logic [15:0] state_7;
assign state_7 = {{7{output_IR_local[8]}}, output_IR_local[8:0]};

logic [15:0] state_5;
assign state_5 = {{5{output_IR_local[10]}}, output_IR_local[10:0]};

logic [15:0] state_11;
assign state_11 = {{ 11{output_IR_local[4] }}, output_IR_local[4:0] };

logic [3:0] gate_set;
assign gate_set = {GateMDR, GateALU, GatePC, GateMARMUX};

four_one_MUX addr2_mux2( .in0(16'b0), .in1(state_10),
                        .in2(state_7), .in3(state_5), .select(ADDR2MUX), .mux_output(output_ADDR2MUX_local) );

four_one_MUX sr1_mux3( .in0(output_IR_local[11:9]), .in1(output_IR_local[8:6]),
                        .in2(3'd6), .in3(), .select(SR1MUX), .mux_output(input_reg_system_local) );
								
two_one_MUX sr2_mux_4( .in0(output_SR2_local), .in1(state_11), .select(output_IR_local[5]), .mux_output(output_SR2MUX_local) );

four_one_MUX pc_mux_5( .in0(output_PC_local + 1'b1), .in1(data_bus_local),
                        .in2(output_ADDR1MUX_local + output_ADDR2MUX_local), .in3(), .select(PCMUX), .mux_output(output_PCMUX_local) );

four_one_MUX_3bit dr_mux6 ( .in0(output_IR_local[11:9]), .in1(3'd7),
                        .in2(3'd6), .in3(), .select(DRMUX), .mux_output(output_DRMUX_local) );

four_one_MUX_4bit_select tri_mux7 ( .in0(output_ADDR1MUX_local + output_ADDR2MUX_local), .in1(output_PC_local), .in2(output_ALU_local), .in3(output_MDR_local), .select(gate_set), .mux_output(data_bus_local) );


register_file reg_file0( .Clk(Clk), .Reset(Reset), .Load(LD_REG), .data_bus(data_bus_local),
                         .DR(output_DRMUX_local), .SR1(input_reg_system_local), .SR2(output_IR_local[2:0]), .output_SR1(output_SR1_local), .output_SR2(output_SR2_local) );

reg_16 MAR( .Clk(Clk), .Reset(Reset), .Load(LD_MAR), .D(data_bus_local), .Data_Out(output_MAR_local) );
reg_16 MDR( .Clk(Clk), .Reset(Reset), .Load(LD_MDR), .D(output_MDRMUX_local), .Data_Out(output_MDR_local) );
reg_16 IR( .Clk(Clk), .Reset(Reset), .Load(LD_IR), .D(data_bus_local), .Data_Out(output_IR_local) );
reg_16 PC( .Clk(Clk), .Reset(Reset), .Load(LD_PC), .D(output_PCMUX_local), .Data_Out(output_PC_local) );

ALU alu0( .A(output_SR1_local), .B(output_SR2MUX_local), .ALU_select(ALUK),
.output_data(output_ALU_local) );

BEN br_en0( .Clk(Clk), .Reset(Reset), .load_cc(LD_CC), .load_branch_en(LD_BEN),
.input_data(data_bus_local), .instructions(output_IR_local[11:9]), .branch_en_output(output_branch_en_local) );

assign output_MAR = output_MAR_local;
assign output_MDR = output_MDR_local;
assign output_IR = output_IR_local;
assign output_PC = output_PC_local;

endmodule
