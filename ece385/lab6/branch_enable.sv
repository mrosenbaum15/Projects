// Inputs: Clk, Reset, load_cc (condition codes), load_branch_en, [15:0] input_data, [2:0] instructions (IR)
// Outputs: branch_en_output (acting at as a FlipFlop)
// Description: This takes in 16 bits of input, and 3 bits of instruction to
//					 determine whether or not a branch should occur. Using the input
//					 bits, BEN will compute the proper NZP values for branching, and then
//					 output it in branch_en_output. 
// Purpose: BEN is needed as an indicator on whether the BR operation should occur.
//				It is instantiated in datapath.sv.
module BEN
(
        input logic Clk,
        input logic Reset,
        input logic load_cc,
        input logic load_branch_en,
        input logic [15:0] input_data,
        input logic [2:0] instructions,
        output logic branch_en_output
);


// nzp arrays: 0-n, 1-z, 2-p for both arrays respectively
logic n, z, p;
logic output_n, output_z, output_p;

// flip-flop part - handling next state of nzp values
always_ff @ (posedge Clk) begin

        if(load_branch_en)
            branch_en_output <= ~( (instructions & {output_n, output_z, output_p}) == 3'b000);
        else if(Reset)
            branch_en_output <= 0;

        if(load_cc) begin
            output_n <= n;
            output_z <= z;
            output_p <= p;
        end

end


// in the following logic, you set z as high if 16 bits of input are zero
// set n high if MSB is a 1
// set p high if MSB is a 0 and all bits are not zero
// otherwise, set to Z
always_comb begin

        if(input_data == 16'b0) begin
            n = 0;
            z = 1;
            p = 0;
        end
        else if(input_data[15] == 0) begin
            n = 0;
            z = 0;
            p = 1;
        end
        else if(input_data[15] == 1) begin
            n = 1;
            z = 0;
            p = 0;
        end
        else begin
            n = 1'hZ;
            z = 1'hZ;
            p = 1'hZ;
        end

end

endmodule
