// module BEN (branch_enable)
// inputs: Clk, Reset, load_cc (condition codes), load_branch_en, instructions (IR)
// output: branch_en_output (acting at as a FlipFlop)
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
logic [2:0] nzp;
logic [2:0] output_nzp;

// flip-flop part - handling next state of nzp values
always_ff @ (posedge Clk) begin

        if(load_branch_en)
            branch_en_output <= ~( (instructions & output_nzp) == 3'b000);
        else if(Reset)
            branch_en_output <= 0;

        if(load_cc) begin
            output_nzp[0] <= nzp[0];
            output_nzp[1] <= nzp[1];
            output_nzp[2] <= nzp[2];
        end

end


// in the following logic, you set z as high if 16 bits of input are zero
// set n high if MSB is a 1
// set p high if MSB is a 0 and all bits are not zero
// otherwise, set to Z
always_comb begin

        if(input_data == 16'b0) begin
            nzp[0] = 0;
            nzp[1] = 1;
            nzp[2] = 0;
        end
        else if(input_data[15] == 0) begin
            nzp[0] = 0;
            nzp[1] = 0;
            nzp[2] = 1;
        end
        else if(input_data[15] == 1) begin
            nzp[0] = 1;
            nzp[1] = 0;
            nzp[2] = 0;
        end
        else begin
            nzp[0] = 1'hZ;
            nzp[1] = 1'hZ;
            nzp[2] = 1'hZ;
        end

end

endmodule
