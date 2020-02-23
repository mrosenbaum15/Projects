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
        output logic [15:0] output_MAR, output_MDR, output_IR, output_PC
        output logic BEN
);

logic [15:0] data_bus_local, output_SR1_local, output_SR2_local;
logic [15:0] output_MAR_local, output_MDR_local, output_IR_local, output_PC_local;
logic [15:0] output_PCMUX_local, output_ADDR2MUX_local, output_ALU_local;
logic [15:0] output_DRMUX_local, output_MDRMUX_local, output_SR2MUX_local, output_ADDR1MUX_local;
logic [15:0] input_reg_system_local;

logic output_branch_en_local;


register_file reg_file0( .Clk(Clk), .Reset(Reset), .Load(LD_REG), .data_bus(data_bus_local),
                         .DR(output_DRMUX_local), .SR1(input_reg_system_local), .SR2(output_IR_local[2:0]), .output_SR1(output_SR1_local), .output_SR2(output_SR2_local) );

reg_16 MAR( .Clk(Clk), .Reset(Reset), .Load(LD_MAR), .D(data_bus_local), .Data_Out(output_MAR_local) );
reg_16 MDR( .Clk(Clk), .Reset(Reset), .Load(LD_MDR), .D(output_MDRMUX_local), .Data_Out(output_MDR_local) );
reg_16 IR( .Clk(Clk), .Reset(Reset), .Load(LD_IR), .D(data_bus_local), .Data_Out(output_IR_local) );
reg_16 PC( .Clk(Clk), .Reset(Reset), .Load(LD_PC), .D(output_PCMUX_local), .Data_Out(output_PC_local) );

ALU alu0( .A(output_SR1_local), .B(output_SR2MUX_local), .ALU_select(ALUK),
.output_data(output_ALU_local) );

branch_enable br_en0( .Clk(Clk), .Reset(Reset), .load_cc(LD_CC), .load_branch_en(LD_BEN),
.input_data(data_bus_local), .instructions(output_IR_local[11:9]), .branch_en_output(output_branch_en_local) );

endmodule
