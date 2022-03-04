//A model of a blog that will passed from the backend to the frontend
export interface IBlog {
    id: number;
    body: string;
    publishedDate: Date;
    title: string;
    topic: string;
    updatedDate: Date;
    author: string;
}