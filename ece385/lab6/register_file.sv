// Inputs: Clk, Reset, Load, [15:0] data_bus, ([2:0] DR, SR1, SR2)
// Outputs: ([15:0] output_SR1, output_SR2)
// Description: For this LC3 implementation, we have an 8x16 register file. This is instantied
//					 as reg_system below. This is implemented using the 2-always method.
//					 In the always_ff section, we can load data into the registers on load high.
//					 A reset indicates that each register should be cleared. In the always_comb
//					 section, the registers are output into either SR1 or SR2
// Purpose:		 This module implements the 8x16 register system and is needed in the datapath.
//					 In datapath.sv, a singular register file is instantiated.
module register_file
(
        input logic Clk,
        input logic Reset,
        input logic Load,
        input logic [15:0] data_bus,
        input logic [2:0] DR,
        input logic [2:0] SR1,
        input logic [2:0] SR2,
        output logic [15:0] output_SR1,
        output logic [15:0] output_SR2
);

// creating 8x16 register system
logic [7:0][15:0] reg_system;

// on rising edge, set all registers to what is on the bus if DR is high
// otherwise, clear everything
always_ff @ (posedge Clk) begin

        if(Load) begin

            case(DR)
                3'b000: reg_system[0] <= data_bus;
                3'b001: reg_system[1] <= data_bus;
                3'b010: reg_system[2] <= data_bus;
                3'b011: reg_system[3] <= data_bus;
                3'b100: reg_system[4] <= data_bus;
                3'b101: reg_system[5] <= data_bus;
                3'b110: reg_system[6] <= data_bus;
                3'b111: reg_system[7] <= data_bus;
            endcase

        end
        else if(Reset) begin

            for(int i = 0; i < 8; ++i)
                reg_system[i] <= 16'h0;

        end

end

// selecting which register system and outputting it into either SR1 or SR2, depending on which is input as high
always_comb begin

        case(SR1)
            3'b000: output_SR1 = reg_system[0];
            3'b001: output_SR1 = reg_system[1];
            3'b010: output_SR1 = reg_system[2];
            3'b011: output_SR1 = reg_system[3];
            3'b100: output_SR1 = reg_system[4];
            3'b101: output_SR1 = reg_system[5];
            3'b110: output_SR1 = reg_system[6];
            3'b111: output_SR1 = reg_system[7];
        endcase

        case(SR2)
            3'b000: output_SR2 = reg_system[0];
            3'b001: output_SR2 = reg_system[1];
            3'b010: output_SR2 = reg_system[2];
            3'b011: output_SR2 = reg_system[3];
            3'b100: output_SR2 = reg_system[4];
            3'b101: output_SR2 = reg_system[5];
            3'b110: output_SR2 = reg_system[6];
            3'b111: output_SR2 = reg_system[7];
        endcase

end

endmodule
