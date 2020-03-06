// Inputs: ([15:0 A, B), [1:0] ALU_select
// Outputs: [15:0] output_data
// Description: Implements Arithmetic Logic Unit using two 16-bit inputs.
//					 Follows LC3 instruction set to perform one of four actions based
//					 off of the ALU_select bit. Either will ADD the inputs, AND them,
//					 NOT input A, or PASS input A to output.
// Purpose:		 The ALU is needed in the datapath and instantiated in datapath.sv.
//					 When an operation is occurring, the ALU handles what happens with
//					 the data, ensuring that the proper operation does occur.
module ALU
(
        input logic [15:0] A,
        input logic [15:0] B,
        input logic [1:0] ALU_select,
        output logic [15:0] output_data
);

// using LC3 architecture for ALU - either ADD A, B, AND them, NOT A, or PASS A
always_comb begin

        if(ALU_select == 2'd0)
            output_data = A + B;
        else if(ALU_select == 2'd1)
            output_data = A & B;
        else if(ALU_select == 2'd2)
            output_data = ~A;
        else
            output_data = A;

end

endmodule
