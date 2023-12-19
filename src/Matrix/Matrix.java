/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Matrix;

/**
 *
 * @author ekaterina
 */
import java.util.Scanner;
public class Matrix {
    int rowsCount;
    int colsCount;
    Double[][] array;
    
     public void inputMatrix(){
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество строк матрицы:");
        rowsCount = in.nextInt();
        System.out.println("Введите количество стролбцов матрицы:");
        colsCount = in.nextInt();
        array=new Double[rowsCount][colsCount];
        System.out.println("Введите 1, если желаете ввести матрицу поэлементно, введите 2, если построчно: ");
        int inputtype=in.nextInt();
        if (inputtype==1){
            for (int i=0;i<rowsCount;++i){
                for (int j=0;j<colsCount;++j){
                   int rowsPosition=i+1;
                   int colsPosition=j+1;
                   System.out.println("Введите элемент "+rowsPosition+" строки и " + colsPosition + " столбца");
                   array[i][j]=in.nextDouble();
                }
           }
        }
        else{    
            in.nextLine();
            for (int i=0;i<rowsCount;++i){
                int rowsPosition=i+1;
                System.out.println("Введите элементы "+rowsPosition+ " строки через пробел:");
                String[] row = in.nextLine().split(" ");
                for (int j=0;j<colsCount;j++){
                    array[i][j]=Double.parseDouble(row[j]);
                } 
            }
        }
    }

    public void outputMatrix(){
        System.out.println();
        System.out.println("Матрица ["+rowsCount+" * "+colsCount+"]:");
        for (int i=0;i<rowsCount;++i){
            for (int j=0;j<colsCount;++j){
                System.out.print(array[i][j]+" ");
            }
            System.out.println(); 
        } 
        System.out.println();
    }
    
    public Matrix matrixMultiplication(Matrix b){
        System.out.println();
        Matrix multiplication=new Matrix();
        if (colsCount==b.array.length){
            System.out.println("Результат умножения:");
            multiplication.rowsCount=rowsCount;
            multiplication.colsCount=b.array[0].length;
            multiplication.array=new Double[multiplication.rowsCount][ multiplication.colsCount];            
            for (int i=0;i<rowsCount;++i){
                for (int j=0;j<b.array[0].length;++j){
                    double sum=0;
                    for (int l=0;l<colsCount;++l){
                        sum+=array[i][l]*b.array[l][j];
                    }
                    multiplication.array[i][j]=sum;
                }     
            }
            for (int i=0;i<multiplication.array.length;++i){
                for (int j=0;j<multiplication.array[0].length;++j){
                    System.out.print(multiplication.array[i][j]+" ");   
                }
                System.out.println();
            }
        }
        else if (rowsCount==b.array[0].length) {
            Scanner in=new Scanner(System.in);
            System.out.println("Прямое умножение осуществить невозможно. Воспроизвести умножение в обратном порядке?");
            String answer=in.nextLine();
            if (answer.equals("y")){answer="yes";}
            if (answer.equals("д")){answer="yes";}
            if (answer.equals("да")){answer="yes";}
            if (answer.equals("yes")){
                System.out.println("Результат умножения:");
                multiplication.rowsCount=b.array.length;
                multiplication.colsCount=colsCount;
                multiplication.array=new Double[multiplication.rowsCount][multiplication.colsCount];
                for (int i=0;i<b.array.length;++i){
                   for (int j=0;j<colsCount;++j){
                       double sum=0;
                       for (int l=0;l<b.array[0].length;++l){
                           sum+=b.array[i][l]*array[l][j];
                       } 
                    multiplication.array[i][j]=sum;
                    }
                 }
            }   
        }
        return multiplication;
    }
    
    public int matrixRang() {
        Matrix m1=new Matrix();
        m1.rowsCount=rowsCount;
        m1.colsCount=colsCount;
        m1.array=new Double[m1.rowsCount][m1.colsCount];
        for (int i=0;i<rowsCount;++i){
            for (int j=0;j<colsCount;++j){
                m1.array[i][j]=array[i][j];
            }
        }
        System.out.println();
        int rang=0;
        for (int i=0;i<m1.array.length-1;++i){
            if(array[i][i]==0.0){
                int allrowsnull=0;
                for (int j1=i+1;j1<m1.array.length;++j1){
                    if (m1.array[j1][i]!=0.0){
                        allrowsnull=1;
                        for (int i1=0;i1<m1.array[0].length;++i1){
                            double promElement;
                            promElement=m1.array[i][i1];
                            m1.array[i][i1]=m1.array[j1][i1];
                            m1.array[j1][i1]=promElement; 
                        }
                        break;
                    } 
                }
                if (allrowsnull==0){
                    break;
                }
            }
            for (int l=i+1;l<m1.array.length;++l){
                if (m1.array[l][i]==0.0){
                    continue;
                }
                else{
                    double kf=m1.array[l][i]/m1.array[i][i];
                    for (int j=0;j<m1.array[0].length;++j){
                        m1.array[l][j]=m1.array[l][j]-m1.array[i][j]*kf;
                    }
                }   
            }
        }
        for (int i=rowsCount-1;i>0;--i){
            if (m1.array[i][i]!=0.0){
                for (int i1=i-1;i1>=0;--i1){
                    if (m1.array[i1][i]!=0.0){
                        double k=m1.array[i][i]/m1.array[i1][i];
                        for (int j1=rowsCount-1;j1>=0;--j1){
                            m1.array[i1][j1]=k*m1.array[i1][j1]-m1.array[i][j1];
                        } 
                    }
                }
            }
        }
        for (int i=0;i<m1.array.length;++i){
            int checknull=0;
            for (int j=0;j<m1.array[0].length;++j){
                if (m1.array[i][j]!=0.0){
                    checknull=1;
                }
            }
            if (checknull==1){
                rang++;
            }
        }
        return rang;
    }
    
    public void transpMatrix(){
        System.out.println();
        for (int i=0;i<rowsCount-1;++i){
            for (int j=i+1;j<colsCount;++j){
                double element=array[i][j];
                array[i][j]=array[j][i];
                array[j][i]=element;
            }
        }
    }
    
    public Matrix solve(Matrix sv){
        Matrix answer=new Matrix();
        answer.rowsCount=this.rowsCount;
        answer.colsCount=1;
        answer.array=new Double[answer.rowsCount][answer.colsCount];
        Matrix extendedMatrix=new Matrix();
        extendedMatrix.rowsCount=rowsCount;
        extendedMatrix.colsCount=colsCount+1;
        extendedMatrix.array=new Double[extendedMatrix.rowsCount][extendedMatrix.colsCount];
        for (int i=0;i<rowsCount;++i){
            for (int j=0;j<colsCount+1;++j){
                if (j==colsCount){
                    extendedMatrix.array[i][j]=sv.array[i][0];
                }
                else{
                    extendedMatrix.array[i][j]=this.array[i][j];
                }
            }
        }
        System.out.println();
        int r1=matrixRang();
        int r2=extendedMatrix.matrixRang();
        if (r1<r2){
            System.out.println("Система линейных уравнений не имеет решений");
        }
        if (r1==r2&&r1<colsCount){
            System.out.println("Система линейных уравнений имеет бесконечное количество решений");
        }
        if (r1==r2&&r1==colsCount){
            for (int i=0;i<rowsCount-1;++i){
                if (this.array[i][i]==0.0){
                    for (int j1=i+1;j1<rowsCount;++j1){
                        if (this.array[j1][i]!=0.0){
                             for (int l=0;l<colsCount;++l){
                                double prElement=this.array[i][l];
                                this.array[i][l]=this.array[j1][l];
                                this.array[j1][l]=prElement;
                                double prElementd=sv.array[i][0];
                                sv.array[i][0]=sv.array[j1][0];
                                sv.array[j1][0]=prElementd;
                             }
                               break;
                        } 
                     }
                }
     
                for (int i1=i+1;i1<rowsCount;++i1){
                    if (this.array[i1][i]==0.0){
                        continue;
                    }
                    else{
                        double k=this.array[i1][i]/this.array[i][i];
                        for (int j=0;j<colsCount;++j){
                            this.array[i1][j]=this.array[i1][j]-this.array[i][j]*k;
                        }
                        sv.array[i1][0]=sv.array[i1][0]-sv.array[i][0]*k;
                    }
                } 
            }
            for (int i=rowsCount-1;i>=1;--i){
                for (int i1=i-1;i1>=0;--i1){
                    if (this.array[i1][1]==0.0){
                        continue;
                    }
                    else{
                        double k=this.array[i1][i]/this.array[i][i];
                        this.array[i1][i]= this.array[i1][i]-k*this.array[i][i];
                        sv.array[i1][0]=sv.array[i1][0]-k*sv.array[i][0];
                    }     
                }
            }
            for (int i=0;i<rowsCount;++i){
                    answer.array[i][0]=sv.array[i][0]/this.array[i][i];
                    System.out.println(answer.array[i][0]);
            }
        }
        return answer;
    }
    
    
    public Matrix pow(int p){
        Matrix matrixPow = new Matrix();
        matrixPow.rowsCount=rowsCount;
        matrixPow.colsCount=colsCount;
        matrixPow.array=new Double[matrixPow.rowsCount][matrixPow.colsCount];
        Matrix matrixMultiplier = new Matrix();
        matrixMultiplier.rowsCount=rowsCount;
        matrixMultiplier.colsCount=colsCount;
        matrixMultiplier.array=new Double[matrixMultiplier.rowsCount][matrixMultiplier.colsCount];
        for (int i=0;i<rowsCount;++i){
            for (int j=0;j<colsCount;++j){
                matrixMultiplier.array[i][j]=array[i][j];
            }
        }
        System.out.println("Результат возведения в степень:");
        for (int p1=1;p1<p;++p1){
            for (int i=0;i<rowsCount;++i){
                for (int j=0;j<colsCount;++j){
                    double sum=0;
                    for (int l=0;l<rowsCount;++l){
                        sum+=this.array[i][l]*matrixMultiplier.array[l][j];
                        
                    }
                    matrixPow.array[i][j]=sum;
                } 
            }
            for (int i=0;i<rowsCount;++i){
                for (int j=0;j<colsCount;++j){
                    matrixMultiplier.array[i][j]=matrixPow.array[i][j];
                }
            } 
        }
        for (int i=0;i<rowsCount;++i){
            for (int j=0;j<colsCount;++j){
                System.out.print(matrixPow.array[i][j]+" ");
            }
            System.out.println();
        }
        return matrixPow;
    }
    
    
        public Matrix solve2(Matrix sv){
        
        Matrix answer=new Matrix();
        answer.rowsCount=this.rowsCount;
        answer.colsCount=1;
        answer.array=new Double[answer.rowsCount][answer.colsCount];
        Matrix extendedMatrix=new Matrix();
        extendedMatrix.rowsCount=rowsCount;
        extendedMatrix.colsCount=colsCount+1;
        extendedMatrix.array=new Double[extendedMatrix.rowsCount][extendedMatrix.colsCount];
        for (int i=0;i<rowsCount;++i){
            for (int j=0;j<colsCount+1;++j){
                if (j==colsCount){
                    extendedMatrix.array[i][j]=sv.array[i][0];
                }
                else{
                    extendedMatrix.array[i][j]=this.array[i][j];
                }
            }
        }
        System.out.println();
        int r1=matrixRang();
        int r2=extendedMatrix.matrixRang();
        if (r1<r2){
            System.out.println("Система линейных уравнений не имеет решений");
        }
        if (r1==r2&&r1<colsCount){
            System.out.println("Система линейных уравнений имеет бесконечное количество решений");
        }
        if (r1==r2&&r1==colsCount){
            for (int i=0;i<rowsCount;++i){
                if (this.array[i][i]==0.0){
                    int checkElement=0;
                    for (int j1=i;j1<colsCount;++j1){
                        if (checkElement==0){
                            for (int i1=i+1;i1<rowsCount;++i1){
                                if (this.array[i1][j1]!=0.0){
                                    checkElement=1;
                                    for (int l=0;l<colsCount;++l){
                                        double prElement=this.array[i][l];
                                        this.array[i][l]=this.array[i1][l];
                                        this.array[i1][l]=prElement;
                                        double prElementd=sv.array[i][0];
                                        sv.array[i][0]=sv.array[i1][0];
                                        sv.array[i1][0]=prElementd;
                                    }
                                    if (j1!=i){
                                        for (int l=0; l<rowsCount;++l){
                                            double prElement=this.array[l][j1];
                                            this.array[l][j1]=this.array[i][i];
                                            this.array[i][i]=prElement;
                                        }
                                    }
                                }
                            } 
                        }
                    }
                }
                if (i!=rowsCount-1){
                    for (int i1=i+1;i1<rowsCount;++i1){
                        if (this.array[i1][i]==0.0){
                            continue;
                        }
                        else{
                            double k=this.array[i1][i]/this.array[i][i];
                            for (int j=0;j<colsCount;++j){
                                this.array[i1][j]=this.array[i1][j]-this.array[i][j]*k;
                            }
                            sv.array[i1][0]=sv.array[i1][0]-sv.array[i][0]*k;
                        }    
                    }    
                }
                if (i!=0){
                    for (int i1=i-1;i1>=0;--i1){
                        if (this.array[i1][i]==0.0){
                            continue;
                        }
                        else{
                            double k=this.array[i1][i]/this.array[i][i];
                            for (int l=0;l<colsCount;++l){
                                this.array[i1][l]= this.array[i1][l]-k*this.array[i][l];
                            }
                            sv.array[i1][0]=sv.array[i1][0]-k*sv.array[i][0];
                        }       
                    }
                }
            }
            for (int i=0;i<rowsCount;++i){
                    answer.array[i][0]=sv.array[i][0]/this.array[i][i];
                    System.out.println(answer.array[i][0]);
            }
        }
        return answer;
    }
}
